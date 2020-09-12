SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS stories{
 id in PRIMARY KEY auto_increment;
 description VARCHAR;
 complete BOOLEAN;
}