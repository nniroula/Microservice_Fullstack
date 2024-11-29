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
4. Eureka-Server = localhost:8761 <br>
5. api-gateway = localhost:8765 <br>


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
-- ```show columns from your_table``` to display column names <br>
-- ```SELECT * FROM table_name;```(in this case product_table) - to display the list of items from the database table <br>
-- Then run the project in Eclipse and provide input in the browser. Check the table as above for the update. <br>


#### ```I) How to run this project in your local device```
1. Run the seed file and make sure that a database is created
2. Run the project in IDE(such as Eclipse or IntelliJ)le


#### ```J) Postman Client for testing API endpoints ```
1. ```Proudct service endpoints:``` <br>
--  POST: -> localhost:8081/api/products <br>
JSON input in Postman is <br>
    {<br>
        "name": "iphone",<br>
        "description": "7 plus",<br>
        "price": 750.00<br>
    }<br>
>blockquote
-- GET product by id -> localhost:8081/api/products/1<br>
-- GET all products -> localhost:8081/api/products<br>
-- DELETE product by id -> localhost:8081/api/products/product/1

2. ```Oder service endpoints``` <br>
-- POST -> <br> localhost:8082/api/orders <br>
JSON formatted input is: <br>
        {<br>
            "skuCode": "SKU CODE HERE", <br>
            "quantity": 10 <br>
        } <br>

-- GET all orders --> localhost:8082/api/orders<br>
-- GET an order by ID --> localhost:8082/api/orders/1<br>
-- DELETE by id --> localhost:8082/api/orders/1<br>

-- response from inventory service
 &emsp; &emsp; &emsp; 
    localhost:8082/api/orders/api/inventory/response/from/inventoryService<br>

    localhost:8082/api/orders/api/inventory/{sku}/quantity

    -- To update inventory from order service
    // update url is localhost:8082/api/orders/api/inventory/{sku}/update



3. ```Inventory service endpoints```
-- POST create an inventory item -> localhost:8083/api/inventory<br>
JSON format is <br>
{ <br>
    "skuCode": "MackBook", <br>
    "quantity": 10 <br>
} <br>
-- GET all inventories -> localhost:8083/api/inventory <br>
-- GET an item by SKU code -> localhost:8083/api/inventory/SkuCodeHere
-- DELETE inventory by sku code -> localhost:8083/api/inventory/SkuCodeHere <br>

<pre>
>blockquote
-- get resposne from inventory micro-service <br>
    &emsp; &emsp; &emsp; 
    localhost:8082/api/orders/api/inventory/response/from/inventoryService<br>

    localhost:8083/api/inventory/response/from/inventoryService
>blockquote
</pre>






////////////////////////////////////////////////////////////////////////////
	// create a product using product Request DTO class
	@PostMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public void createProductUsingProductDTO(@RequestBody ProductRequestDTO newProductRequestDTO){
		productService.createProductUsingProductRequestDto(newProductRequestDTO);
	}
	
	/* a. create a product using Product modal directly */
	public Product createProduct(Product product) {
		Product productCreataed = productRepo.save(product);
		return productCreataed;
	}
	
	
	//b. create a product using Product DTO class
	public ProductRequestDTO createProductUsingProductRequestDto(ProductRequestDTO productRequestDto) {
		
		// convert ProductRequestDTO into Product JPA Entity and save it to database
		Product requestDtoMappedProduct = modelMapper.map(productRequestDto, Product.class);
		 Product createdProduct = productRepo.save(requestDtoMappedProduct);
		 
		 //Convert Product JPA Entity into ProductRequestDTO type and return it
		 ProductRequestDTO createdProductRequestDTO = modelMapper.map(createdProduct, ProductRequestDTO.class);
		return createdProductRequestDTO;
	}


    ////////////////// endpoints //////////////////////
    /api/inventory/item/{sku} -> to access inventory service from order service