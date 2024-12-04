-- db table(s) will be updated using spring boot project
DROP DATABASE IF EXISTS inventory_service_db;
create database inventory_service_db;
use inventory_service_db;

DROP table IF EXISTS inventory_service_table;
CREATE table inventory_service_table(
					id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
					sku_code VARCHAR(255),
					quantity int
				);