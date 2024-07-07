## postgresql
```
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    password_salt VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

create table if not exists leads (
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
    container_name: my-postgres
    environment:
      - POSTGRES_DB=postgres
      - POSTGRES_USER=root
      - POSTGRES_PASSWORD=root
    ports:
      - "5433:5432"
    volumes:
      - postgres-data:/var/lib/postgresql/data

volumes:
  postgres-data:
    name: java-exercises
```
Estas são as configurações básicas para rodar a aplicação
