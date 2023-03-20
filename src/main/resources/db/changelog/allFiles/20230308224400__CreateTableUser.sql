-- changeset liquibaseuser:1
CREATE TABLE IF NOT EXISTS user_account (
	id SERIAL PRIMARY KEY,
	nickname VARCHAR(10) NOT NULL,
	email VARCHAR(40) NOT NULL,
	password VARCHAR(40) NOT NULL,
	age INT NOT NULL
);

-- rollback DROP TABLE user_account;
