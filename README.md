### ```eOrderSys```

#### Description
An electronic product purchase system
It's a fullstack online product ordering service that is built leveraging micro-service with Spring Cloud. <br>
Technologies: Spring Data JPA, Spring Web, Spring Boot Devtools, Spring MySQL Driver, MySQL, Hibernate, embedded TomCat Server, ReactJS, Java, JavaScript, Bootstrap, HTML, CSS,
API Gateway (Entery Point to access RESTful API endpoints of micro-services), Eureka Server(Naming Server), FeignClient(For inter-service communication)


#### ```A) Port numbers for each service```
1. product-service = 8081 <br>
2. inventory-service = 8082 <br>
3. order-service = 8083 <br>


#### ```B) Different Micro-services```
#### 1. Product Service
It is a micro-service based on Spring Cloud framework <br>
--- create product, save product to datatbase, delete and update product <br>

#### 2. Inventory Service
It is a micro-service based on Spring Cloud framework <br>
-- Checks product inventory <br>

#### 3. Order Service
It is a micro-service based on Spring Cloud framework <br>
-- Places an order if an inventory is available for a particular product. <br>


#### ```C) React-UI```
This provides front-end user interface <br>
-- built using create-react-app <br>


#### ```D) Micro-service API Endpoints```
1. bluh bluh bluh
2. bluh bluh bluh


#### ```E) API Gateway Endpoints```
1. bluh bluh bluh


#### ```F) React-UI API Endpoints```
1. bluh bluh bluh <br>
2. bluh bluh bluh <br>


#### ```G) How to create MYSQL database```
1. bluh bluh bluh <br>
2. bluh bluh bluh <br>
3. bluh bluh bluh <br>
4. bluh bluh bluh <br>
5. bluh bluh bluh <br>
6. bluh bluh bluh <br>


#### ```H) How to run seed.sql file```
Start your MySQL RDBMS in your device (Click on Apple icon on top left corner of your Mac laptop > System Preferences > MySQL) <br>
Open this project in Eclipse IDE <br>
Open up terminal in your device and cd into the folder locaiton of your seed.sql file. Then type ```mysql``` and hit enter. If this does not work, then run the command ```mysql -u root -p```. Provide password for your MySQL RDBMS. <br>
-- Then run the command: ```source seed.sql;``` - it's your SEED FILE <br>
-- Use command ```show databases;``` to check your available databases. <br> 
-- Run the command ```use your_db```(your database name, in this case product_service_db) to connect to your database <br>
-- Run the command ```show tables;``` to show available database tables <br>
-- ```SELECT * FROM table_name;```(in this case product_table) - to display the list of items from the database table <br>
-- Then run the project in Eclipse and provide input in the browser. Check the table as above for the update. <br>