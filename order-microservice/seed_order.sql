-- db table(s) will be updated using spring boot project
DROP DATABASE IF EXISTS order_service_db;
create database order_service_db;
use order_service_db;

DROP table IF EXISTS order_service_table;
CREATE table order_service_table(
								id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
								sku_code VARCHAR(255),
								quantity int
							);

-- INSERT INTO product_service_table(id, product_name, product_description, product_price) 
--		VALUES(1, "test product", "test description", 0.00); 