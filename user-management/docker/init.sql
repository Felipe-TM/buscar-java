CREATE DATABASE IF NOT EXISTS userdb_01;
CREATE TABLE IF NOT EXISTS userdb_01.user_table(
	id int NOT NULL AUTO_INCREMENT,
	name varchar(255),
	phone_number varchar(255),
	username varchar(255),
	password varchar(255),
	email varchar(255),
	is_account_non_expired boolean,
	is_account_non_locked boolean,
	is_credentials_non_expired boolean,
	is_enabled boolean,
	PRIMARY KEY (id)
);