package com.persistentcoding.product_microservice.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.persistentcoding.product_microservice.dto.ProductRequestDTO;
import com.persistentcoding.product_microservice.dto.ProductResponseDTO;
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
	
	// create a product using product Request DTO class - works fine
//	@PostMapping("/product")
//	@ResponseStatus(HttpStatus.CREATED)
//	public void createProductUsingProductDTO(@RequestBody ProductRequestDTO newProductRequestDTO){
//		productService.createProductUsingProductRequestDto(newProductRequestDTO);
//	}
	
	/* retrieve product by its id by using product table */
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product getProductById(@PathVariable Long id) {
		Product retrievedProduct = productService.getProductById(id);
		return retrievedProduct;
	}
	
	// retrieve product by id using product DTO class
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductResponseDTO> getProductDetails(@PathVariable("id") long id){
		ProductResponseDTO productResponseDto = productService.getProductByIdUsingProductDto(id);
		return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
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
	
	////////////////////////////////////////////////////////////////////
	////////////// Product Service Related functions ///////////////////
	////////////////////////////////////////////////////////////////////
	
	/* get product price by skuCode */
	@GetMapping("/{skuCode}/price")
	public BigDecimal getProductUnitPrice(@PathVariable("skuCode") String skuCode) {
		BigDecimal productUnitPrice = productService.getProductPriceBySkuCode(skuCode);
		return productUnitPrice;
	}
	
	@GetMapping("/{skuCode}/name") 
	public String getProductNameBySkuCode(@PathVariable("skuCode") String skuCode)  {
		String productName = productService.getProductName(skuCode);
		return productName;
	}
	
	@GetMapping("/{skuCode}/description")
	public String getProductDescriptionBySkuCode(@PathVariable("skuCode") String skuCode) {
		String productDescription = productService.getProductDescription(skuCode);
		return productDescription;
	}
}
