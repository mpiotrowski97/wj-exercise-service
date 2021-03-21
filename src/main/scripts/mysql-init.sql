DROP DATABASE IF EXISTS wj_exercise_service;
DROP USER IF EXISTS `exercise_service`@`%`;
CREATE DATABASE IF NOT EXISTS wj_exercise_service CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `exercise_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `wj_exercise_service`.* TO `exercise_service`@`%`;
FLUSH PRIVILEGES;
