CREATE DATABASE todolist;
\c todolist
CREATE TABLE tasks(id SERIAL PRIMARY KEY, description VARCHAR, categoryId INTEGER );
CREATE TABLE categories (id SERIAL PRIMARY KEY, name VARCHAR);
CREATE DATABASE todolist_test WITH TEMPLATE todolist;

--SET MODE PostgreSQL;
--
----CREATE TABLE IF NOT EXISTS tasks(
---- id int PRIMARY KEY auto_increment,
---- description VARCHAR,
---- categoryId int,
------ complete BOOLEAN
----);
----
----CREATE TABLE IF NOT EXISTS categories(
----  id int PRIMARY KEY auto_increment,
----  name VARCHAR
------ complete BOOLEAN
----);