SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tasks(
 id int PRIMARY KEY auto_increment,
 description VARCHAR,
 categoryId int,
-- complete BOOLEAN
);

CREATE TABLE IF NOT EXISTS categories(
  id int PRIMARY KEY auto_increment,
  name VARCHAR
);