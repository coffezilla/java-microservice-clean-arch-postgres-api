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