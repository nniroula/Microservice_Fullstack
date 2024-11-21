package com.persistentcoding.product_microservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.persistentcoding.product_microservice.modal.Product;
import com.persistentcoding.product_microservice.service.ProductService;


@RestController
@RequestMapping("/api/products")
public class ProductRestController {
	
	private final ProductService productService;
	
	// constructor injection of product service - 	@Autowired is not needed
	public ProductRestController(ProductService productServis) {
		this.productService = productServis;
	}
	
	/* create a product.
	 * @RequestBody means how you get the data - here using dto class 
	*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createProduct(@RequestBody Product newProduct) {
		productService.createProduct(newProduct);
	}
	
	/* retrieve product by its id*/
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProductById(@PathVariable Long id) {
		Product retrievedProduct = productService.getProductById(id);
		return retrievedProduct;
	}
	
	/* retrieve all the products */
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	/* delete product by its id */
	@DeleteMapping("product/{id}")
	public String deleteProductById(@PathVariable long id) {
		//Product productFromDB = getProductById(id);
		productService.deleteProductById(id);
		
		String deletedItem = "Product with id " + id +  " deleted successfully";
		return deletedItem;
	}
	
}
