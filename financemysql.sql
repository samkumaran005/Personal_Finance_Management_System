-- CREATE DATABASE finance_db;
USE finance_db;
-- drop table income;
-- drop table expense;
-- drop table users;
-- CREATE TABLE users (
--   id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   username VARCHAR(100) UNIQUE NOT NULL,
--   password VARCHAR(255) NOT NULL
-- );

-- CREATE TABLE income (
--   id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   amount DOUBLE NOT NULL,
--   source VARCHAR(255),
--   date DATE,
--   user_id BIGINT,
--   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

-- CREATE TABLE expense (
--   id BIGINT AUTO_INCREMENT PRIMARY KEY,
--   amount DOUBLE NOT NULL,
--   category VARCHAR(255),
--   date DATE,
--   user_id BIGINT,
--   FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
-- );

-- sample user: username = demo, password = demo123 (plaintext here; in production hash passwords)
-- INSERT INTO users (username, password) VALUES ('demo', 'demo123');
select * from users;