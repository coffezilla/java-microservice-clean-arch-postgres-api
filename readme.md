# Teste de Desenvolvedor Java
Este é um projeto de um micro serviço que cadastra leads (contatos com nome e email) em um banco de dados com autenticação. Neste micro serviço foi adicionado um sistema de autenticação de usuário para lidar com as criações de tokens para a utilização dos endpoints.

## Neste projeto
- Micro serviço utilizando Java Jersey
- Banco de dados PostgreSQL
- Conexão com banco de dados utilizando JDBC
- Arquitetura do projeto desenvolvido em camadas
- Autenticação com JWT
- Projeto com Maven

## Camadas da arquitetura
- Model (Database)
- Repository (Lógica)
- Resource (Endpoints)
- Service (Regra de negócio)
- Config (Configurações)

## Requisitos para testar
### Testar todo o projeto local
- Docker
- Java SDK 17
- IDE: IntelliJ (https://www.jetbrains.com/pt-br/idea/)
- Database Manager: DBeaver (https://dbeaver.io/download/)
- Documentação da api online: https://mslead-api-documentation.netlify.app/
- Teste de api: Insomnia (https://insomnia.rest/download)
  - Arquivo com todos os endpoints para serem carregados no Insomnia: /documents/Insomnia_2024-07-07.json
### Testar online (Hospedado no Render - https://render.com/)
- Endereço da API: https://msleads.onrender.com
  - Exemplo: https://msleads.onrender.com/api/users [GET]
  - OBS: Este projeto está em uma conta FREE, portanto pode apresentar instabilidades constantes no Render.


## Banco de dados (PostgreSQL)
- Nome do banco de dados: postgres
- Nome do usuário: root
- Senha do usuário: root
- Porta: 5433 (O padrão é 5432, porém eu utilizo 5433 para evitar conflito caso esteja rodando outro projeto na porta padrão)
```
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    password_salt VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

create table leads (
	id SERIAL primary key,
	name VARCHAR(255) not null,
	email VARCHAR(255) not null,
	created_at TIMESTAMP not null
)
```
## Docker compose
```version: '3'
services:
  db:
    image: postgres:13
    container_name: postgres-msleads
    environment:
      - POSTGRES_DB=postgres
      - POSTGRES_USER=root
      - POSTGRES_PASSWORD=root
    ports:
      - "5433:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data
      - ./docker-init-scripts:/docker-entrypoint-initdb.d

volumes:
  postgres-data:
    name: java-msleads
```
## Startando a aplicação local
- Inicie o Docker em sua máquina
- Acesse a raíz do projeto pelo terminal e execute o arquivo docker-compose.yml que está dentro dele:
  - `$ docker-compose up`
- Ao executar o comando, o Docker criará um container chamado msleads com uma imagem do PostgreSQL. Além disso ele irá adicionar o schema com duas tabelas: users e leads.
- Agora basta dar o start no servidor abrindo o projeto no IntelliJ executando a classe Main.

## Troubleshooting
- Certifique-se de que o Docker esteja executando
- Certifique-se de que as dependências Maven foram instaladas pelo IntelliJ
- Certifique-se de que os endpoints estão corretos
- Certifique-se de que as portas estão corretas: 5433 para o banco de dados, 8080 para o servidor

## Testando a aplicação local
- **Documentação da API**: Utilize a documentação contendo todos os endpoints e variáveis para testar
- **Insomnia**: Importe o arquivo Insomnia_2024-07-07.json para o seu Insomnia para que sejam carregados todos os endpoints para testes.

## Passo a passo de teste
### 1 Cadastro de usuário
Primeiro crie um usuário para poder habilitar o login de acesso:

`[POST] /api/users`
````JSON
// JSON
{
  "name": "Usuário a",
  "email": "usuarioa@example.com",
  "password": "123123"	
}
````
### 2 Login, recuperação de token
`[POST] /api/users/login`
````JSON
// JSON
{
  "email": "usuarioa@example.com",
  "password": "123123"
}
````
````JSON
// RETORNO
{
  "id": 5,
  "email": "usuarioa@example.com",
  "name": "Usuario a",
  "createdAt": "2024-07-08T03:35:49.161505",
  "password": null,
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvYUBleGFtcGxlLmNvbSIsImlhdCI6MTcyMDQwOTc1NywiZXhwIjoxNzIwNDEzMzU3fQ.113Ov4pxtHBswAquQ7nIoi_fXVkqzDZFpdLVv11ycGE",
  "passwordHash": null,
  "passwordSalt": null
}
````
#### Retorno

### 3 Cadastro de novo lead com a autenticação
`[POST] /api/leads`
````JSON
// JSON
{
  "name": "Lead A",
  "email": "leada@example.com"
}
````
````TEXT
// AUTENTICAÇÃO BEARER (HEADER)
Bearer yJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c3VhcmlvYUBleGFtcGxlLmNvbSIsImlhdCI6MTcyMDQwOTc1NywiZXhwIjoxNzIwNDEzMzU3fQ.113Ov4pxtHBswAquQ7nIoi_fXVkqzDZFpdLVv11ycGE
````

### Outros testes
Utilize as documentação da api online (https://mslead-api-documentation.netlify.app/) para verificar todos os outros endpoints disponíveis para deletar, excluir ou editar usuários e leads com autenticação.

### Outros endpoints
#### /auth
- POST /api/users/login

#### /leads
- GET /api/leads (Necessita token)
- GET /api/leads/**n** (Necessita token)
- POST /api/leads (Necessita token)
- PUT /api/leads/**n** (Necessita token)
- DELETE /api/leads/**n** (Necessita token)

#### /users
- GET /api/users (Necessita token)
- GET /api/users/**n** (Necessita token)
- POST /api/users
- PUT /api/users/**n** (Necessita token)
- DELETE /api/users/**n** (Necessita token)