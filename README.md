### ```eOrderSys```

#### Description
eOrderSys is an online product purchase system, that is built leveraging micro-service with Spring Cloud and ReactJS tech stacks. <br>
Technologies used are: ```Spring Boot 3.3.5```, ```Spring Framework 6```, ```Spring Data JPA```, ```Spring Web```, Spring Boot Devtools, ```Spring MySQL Driver```, ```MySQL```, ```Hibernate```, embedded TomCat Server, ```ReactJS```, ```Java 17```, ```JavaScript```, Bootstrap, HTML, CSS, ```API Gateway``` (Entery Point to access RESTful API endpoints of micro-services), ```Eureka Server```(Service Registry Server), and ```FeignClient```(For inter-service communication)

<hr>
<hr>

#### ```A) Different Micro-services```

#### 1. Order Service
Provides product sku code and quantity that are consumed by product service and inventory service to respond back to it with reequested data. It's main functionality is to place an order after verfying product availability with inventory service as well as retrieving the unit price for a particular product from the product service.<br>
-- Implemented ```CRUD``` operations: create, update, delete and retrieve an order to and from an order service database.  <br>

#### 2. Inventory Service
Provides product sku code and quantity that are consumed by Order micro-service to place an order. It's main functionality is to check an availability of a product in an inventory database for placning an order.<br>
-- Implemented ```CRUD``` operations: create, update, delete and retrieve product in an inventory <br>

#### 3. Product Service
Provides product name, price, sku code and description that are consumed by Order micro-service to place an order.<br>
-- Implemented ```CRUD``` operations: create product, retrieve product from datatbase, delete and update product <br>

<hr>
<hr>

#### ```B) Key technologies and Concepts```
1. ```Eureka Server```: Programmatically hard coding the url of one micro-service to communicate with another service is not the better approach as url may change in future. With Eureka server, you can instead use names of services registered with it to communicate among them. We need to balance load between instances of micro-services when they come and go. Assume that instance of a microservice running on the port 8001 goes down and you want to send laod to another instance of same micro-service running on port 8002. How to do it? By using Service Registry or Naming Server, which is Eureka Service Registry server. <br>

2. ```FeignClient```: It is used for inter-service communicaton and load balancing.<br> 

3. ```API Gateway```: It's an entry point to all backend APIs. Requests coming from front-end/UI first go to API Gateway and then API Gateway forwards them to appropriate micro-service. <br>

4. ```Admin Server```: It is used to monitor and manage all APIs(micro-services) at one place.<br>

5. ```Config Server```: It is used to separate application code from application.properties file. It externalizes configuration setting from micro-services. You need to load properites file from outside using Config server. So, it provides ```loose coupling```.<br>

6. ```Zipkin Server```: It is used for dristributed tracing of our requests, such as how many micro-services are involved in processing a single request. Download it from the Interneet and run it with the command ```java -jar your-zipkin-jar-file```<br>

8. ```Resilience4j```: If one micro-sevice fails, it may impact another micro-service. How to avoid it? By using Circuit Breaker Framework called Resilience4j. <br>

<hr>
<hr>

#### ```C) Port numbers for each service```
1.  product-service = 8081 <br>
2.  inventory-service = 8082 <br>
3.  order-service = 8083 <br>
4.  Eureka-Server = 8761 <br>
5.  api-gateway = 8765 <br>

<hr>
<hr>

#### ```D) API Gateway Endpoints```

http://localhost:8765/PRODUCT-MICROSERVICE/api/products/c/name works when in application.properties file is spring.cloud.gateway.discovery.locator.enabled = true, However, when it is commented, following works with api-gateway and is the better way to implement api gateway in spring cloud. <br>

&emsp; D1. ```GET Requests for PRODUCT-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/products to retrieve all products<br>

&emsp; &emsp; 2. &emsp;  http://localhost:8765/api/products/{skuCode}/name to retrieve name of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/products/linux/name <br>

&emsp; &emsp; 3. &emsp;  http://localhost:8765/api/products/{skuCode}/price to retrieve price of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/products/linux/price <br>

&emsp; &emsp; 4. &emsp;  http://localhost:8765/api/products/{skuCode}/description to retrieve description of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/products/linux/description <br>

&emsp; D2. ```POST Requests for PRODUCT-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/products to create a product<br>
&emsp; &emsp; &emsp;  &emsp; JSON body in Postman Client is <br>
&emsp; &emsp; &emsp;  &emsp;  { <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "name": "Ubuntu", <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "description": "Linux Laptop", <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "price": 899.00, <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "skuCode": "linux" <br>
&emsp; &emsp; &emsp;  &emsp;    } <br>

&emsp; D3. ```DELETE Requests for PRODUCT-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  http://localhost:8765/api/products/product/{id} to delete a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/products/product/1 <br>

&emsp; D4. ```UPDATE Requests for PRODUCT-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; &emsp;

<hr>

&emsp; D5. ```GET Requests for INVENTORY-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 4. &emsp;  localhost:8765/api/inventory/response/from/inventoryService to check inter-service communication<br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/inventory to retrieve all inventories<br>

&emsp; &emsp; 2. &emsp;  http://localhost:8765/api/inventory/{skuCode} to retrieve an inventory<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/inventory/linux <br>

&emsp; &emsp; 3. &emsp;  http://localhost:8765/api/inventory/{skuCode}/quantity to retrieve quantity of an inventory<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; http://localhost:8765/api/inventory/linux/quantity <br>


&emsp; D6. ```POST Requests for INVENTORY-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/inventory  to creat an inventory <br>
&emsp; &emsp; &emsp;  &emsp; JSON body in Postman Client is <br>
&emsp; &emsp; &emsp;  &emsp;  { <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "skuCode": "linux", <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "quantity": 500 <br>
&emsp; &emsp; &emsp;  &emsp;    } <br>

&emsp; D7. ```UPDATE Requests for INVENTORY-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/inventory/{skuCode}/update  to update quantity in inventory <br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/inventory/linux/update  <br>
&emsp; &emsp; &emsp;  &emsp; JSON body in Postman Client is <br>
&emsp; &emsp; &emsp;  &emsp;  { <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "quantity": 500 <br>
&emsp; &emsp; &emsp;  &emsp;    } <br>

&emsp; D8. ```DELETE Requests for INVENTORY-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/products/product/{id} to delete an inventory <br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/products/product/1
<hr>

&emsp; D9. ```GET Requests for ORDER-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/orders/api/inventory/response/from/inventoryService to connect to inventory service <br>
&emsp; &emsp; 2. &emsp;  localhost:8765/api/orders to retrieve all orders <br>

&emsp; &emsp; 3. &emsp;  localhost:8765/api/orders/api/products/{skuCode}/name to retrieve name of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/orders/api/products/linux/name<br>

&emsp; &emsp; 4. &emsp; localhost:8765/api/products/{skuCode}/price to retrieve price of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/products/linux/price<br>

&emsp; &emsp; 5. &emsp;  localhost:8765/api/products/{Sku}/description to retrieve description of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/products/Linux/description<br>

&emsp; &emsp; 6. &emsp;  localhost:8765/api/orders/api/inventory/{SkuCOde}/quantity to retrieve quantity of a product<br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/orders/api/inventory/LIN/quantity <br>


&emsp; D10. ```POST Requests for ORDER-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/orders to creat an order <br>
&emsp; &emsp; &emsp;  &emsp; JSON body in Postman Client is <br>
&emsp; &emsp; &emsp;  &emsp;  { <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "skuCode": "linux", <br>
&emsp; &emsp; &emsp;  &emsp;&emsp; "quantity": 500 <br>
&emsp; &emsp; &emsp;  &emsp;    } <br>


&emsp; D11. ```DELETE Requests for ORDER-MICROSERVICE using API Gateway```: <br>
&emsp; &emsp; 1. &emsp;  localhost:8765/api/orders/{id} to delete an order <br>
&emsp; &emsp; &emsp; &emsp; &emsp; Ex:- &nbsp;&nbsp; localhost:8765/api/orders/1

<hr>

&emsp; D12. ```How to create those end points?```
- Go to localhost:8761 for Eureka sevice registry server<br>
&emsp; &emsp; &emsp; copy the name PRODUCT-MICROSERVICE<br>
&emsp; &emsp; &emsp; copy the url INVENTORY-MICROSERVICE<br>
&emsp; &emsp; &emsp; copy the url ORDER-MICROSERVICE<br>
- Get the url for API gateway -> localhost:8765
- Put them together -> api-gateway/order-service/inventory-service-url <br>
&emsp; &emsp; Example:- &emsp; localhost:8765/ORDER-MICROSERVICE/api/inventory<br>

- Once you make the API Gateway urls -> run them in the browser and make sure they all work. Following is the right way to make API-Gateway urls, however, for some technical reasons, it's not working in my Postman Client or the browser. <br>
&emsp; &emsp; &emsp; a. url for product service to get all products <br>
&emsp; &emsp; &emsp;&emsp; &emsp; ```http:localhost:8765/PRODUCT-MICROSERVICE/api/products``` <br>
&emsp; &emsp; &emsp; b. url for inventory service to get all inventorys <br>
&emsp; &emsp; &emsp;&emsp; &emsp; ```http:localhost:8765/INVENTORY-MICROSERVICE/api/inventory``` <br>
&emsp; &emsp; &emsp; c. .url for order service to get all orders <br>
&emsp; &emsp; &emsp;&emsp; &emsp; ```http:localhost:8765/ORDER-MICROSERVICE/api/orders``` <br>

<hr>
<hr>

#### ```E) Micro-service API Endpoints Before implementing Spring Cloud API Gateway```

&emsp; E1. ```Product micro-service API Endpoints```: <br>


- POST create a product -> localhost:8081/api/products <br>
&emsp; &emsp; &emsp;JSON input in Postman is <br>
&emsp; &emsp; &emsp;    {<br>
&emsp; &emsp; &emsp; &emsp;      "name": "iphone",<br>
&emsp; &emsp; &emsp; &emsp;       "description": "7 plus",<br>
&emsp; &emsp; &emsp;   &emsp;     "price": 750.00<br>
&emsp; &emsp; &emsp;    }<br>

- GET product by id -> localhost:8081/api/products/1<br>
- GET all products -> localhost:8081/api/products<br>
- DELETE product by id -> localhost:8081/api/products/product/1

<hr>

&emsp; E2. ```Inventory service API Endpoints```: <br>
- POST create an inventory item -> localhost:8083/api/inventory<br>
&emsp; &emsp; &emsp;JSON format is <br>
&emsp; &emsp; &emsp;        { <br>
&emsp; &emsp; &emsp;&emsp;     "skuCode": "MackBook", <br>
&emsp; &emsp; &emsp;&emsp;     "quantity": 10 <br>
&emsp; &emsp; &emsp;        } <br>
- GET all inventories -> localhost:8083/api/inventory <br>
- GET an item by SKU code -> localhost:8083/api/inventory/SkuCodeHere
- DELETE inventory by sku code -> localhost:8083/api/inventory/SkuCodeHere <br>

<hr>

&emsp; E3. ```Order micro-service API Endpoints```: <br>

- POST create an order -> localhost:8082/api/orders <br>
&emsp; &emsp; &emsp;JSON formatted input is: <br>
&emsp; &emsp; &emsp;        {<br>
&emsp; &emsp; &emsp; &emsp;     "skuCode": "SKU CODE HERE", <br>
&emsp; &emsp; &emsp; &emsp;     "quantity": 10 <br>
&emsp; &emsp; &emsp;        } <br>

- GET all orders --> localhost:8082/api/orders<br>
- GET an order by ID --> localhost:8082/api/orders/1<br>
- DELETE an order by id --> localhost:8082/api/orders/1<br>

<hr>
<hr>


#### ```F) React-UI```
This provides front-end user interface <br>
-- built using create-react-app <br>

<hr>
<hr>


#### ```G) React-UI API Endpoints```
1. bluh bluh bluh <br>
2. bluh bluh bluh <br>

<hr>
<hr>

#### ```H) How to create MYSQL database```
1. bluh bluh bluh <br>
2. bluh bluh bluh <br>
3. bluh bluh bluh <br>
4. bluh bluh bluh <br>
5. bluh bluh bluh <br>
6. bluh bluh bluh <br>

<hr>
<hr>

#### ```I) How to run seed.sql file```
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

<hr>
<hr>

#### ```J) How to run this project in your local device```
1. Run the seed file and make sure that a database is created
2. Run the project in IDE(such as Eclipse or IntelliJ)le

<hr>
<hr>

#### ```K) Postman Client for testing API endpoints ```

-- response from inventory service
 &emsp; &emsp; &emsp; 
    localhost:8082/api/orders/api/inventory/response/from/inventoryService<br>

    localhost:8082/api/orders/api/inventory/{sku}/quantity

    -- To update inventory from order service
    // update url is localhost:8082/api/orders/api/inventory/{sku}/update


3. ```Inventory service endpoints```


<pre>
>blockquote
-- get resposne from inventory micro-service <br>
    &emsp; &emsp; &emsp; 
    localhost:8082/api/orders/api/inventory/response/from/inventoryService<br>

    localhost:8083/api/inventory/response/from/inventoryService
>blockquote
</pre>

<hr>
<hr>






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