### ePurchase Service

#### Description
It's a fullstack online product ordering service that is built leveraging micro-service with Spring Cloud. <br>
Technologies: Spring Data JPA, Spring Web, Spring Boot Devtools, Spring MySQL Driver, MySQL, Hibernate, embedded TomCat Server, ReactJS, Java, JavaScript, Bootstrap, HTML, CSS,
API Gateway (Entery Point to access RESTful API endpoints of micro-services), Eureka Server(Naming Server), FeignClient(For inter-service communication)


#### 1. Product Service
It is a micro-service based on Spring Cloud framework <br>
--- create product, save product to datatbase, delete and update product <br>

#### 2. Inventory Service
It is a micro-service based on Spring Cloud framework <br>
-- Checks product inventory <br>

#### 3. Order Service
It is a micro-service based on Spring Cloud framework <br>
-- Places an order if an inventory is available for a particular product. <br>

#### React-UI
This provides front-end user interface <br>
-- built using create-react-app <br>