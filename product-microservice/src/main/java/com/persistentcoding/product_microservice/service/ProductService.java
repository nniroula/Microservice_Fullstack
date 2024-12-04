package com.persistentcoding.product_microservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.persistentcoding.product_microservice.dto.ProductRequestDTO;
import com.persistentcoding.product_microservice.dto.ProductResponseDTO;
import com.persistentcoding.product_microservice.modal.Product;
import com.persistentcoding.product_microservice.repo.ProductRepository;
import com.thoughtworks.xstream.mapper.Mapper;

/* implements your business logics in this class to save data to database table 
 * To save to databse table, you need JpaRepository auto-wired */

@Service
public class ProductService {
	private final ProductRepository productRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/* constructor injection of Product Repository. @Autowired is optional in constructor injection */
	public ProductService(ProductRepository productRepository) {
		this.productRepo = productRepository;
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
	
	
	
	/*1. get product by Id */
	public Product getProductById(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	/* 2. get product by Id using product DTO */
	public ProductResponseDTO getProductByIdUsingProductDto(Long id) {
		Optional<Product> product = productRepo.findById(id);
		ProductResponseDTO productResponseDto = modelMapper.map(product, ProductResponseDTO.class);
		return productResponseDto;
	}
	
	/* get product by Sku code */
	public BigDecimal getProductPriceBySkuCode(String skuCode) {
		Product product = productRepo.findBySkuCode(skuCode);
		return product.getPrice();
	}
	
	
	/* get all products */
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	/* delete product by its id */
	public void deleteProductById(long id) {
		productRepo.deleteById(id);
	}
	
	public String getProductName(String skuCode) {
//		String productName = productRepo.findProductNameBySku(skuCode);
//		String productName = productRepo.findBySkuCode(skuCode);
//		return productName;
		Product productObject = productRepo.findBySkuCode(skuCode);
		return productObject.getName();
	}
	
	public String getProductDescription(String skuCode) {
//		String productDescription = productRepo.findProductDescriptionBySku(skuCode);
//		return productDescription;
		Product productObject = productRepo.findBySkuCode(skuCode);
		return productObject.getDescription();
	}
}
