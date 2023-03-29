-- changeset liquibaseuser:1
CREATE TABLE IF NOT EXISTS game (
	id SERIAL PRIMARY KEY,
	nameGame VARCHAR(10) NOT NULL
);
-- rollback DROP TABLE game;