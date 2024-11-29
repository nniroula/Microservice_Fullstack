-- db table(s) will be updated using spring boot project
DROP DATABASE IF EXISTS product_service_db;
create database product_service_db;
use product_service_db;

DROP table IF EXISTS product_service_table;
CREATE table product_service_table(
								id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
								product_name VARCHAR(255),
								product_sku_code VARCHAR(255),
								product_description VARCHAR(255),
								product_price decimal
							);

-- INSERT INTO product_service_table(id, product_name, product_description, product_price) 
--		VALUES(1, "test product", "test description", 0.00); 