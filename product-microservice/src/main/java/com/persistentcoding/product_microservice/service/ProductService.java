package com.persistentcoding.product_microservice.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persistentcoding.product_microservice.modal.Product;
import com.persistentcoding.product_microservice.repo.ProductRepository;

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
	
	
	/* create a product using Product modal directly */
	public Product createProduct(Product productRequestDto) {
		Product product = productRepo.save(productRequestDto);
		return product;
	}
	/*
	// create a product using DTO to madal class mapping
	public Product createProductUsingDTO(ProductDTO productRequestDto) {
		// map product request to product modal -> use ModelMapper dependency in pom.xml file
		// create @Configuration for model mapper in config package and @Autowired it here
		//ModelMapper modelMapper = new ModelMapper();
		Product product = new Product();
		ProductDTO productDto = modelMapper.map(product, ProductDTO.class);
		
		
		ModelMapper modelMapper = new ModelMapper();
		TypeMap<Product, ProductDTO> typeMap = modelMapper.createTypeMap(Product.class, ProductDTO.class);
		typeMap.addMappings(mapper -> {
		    mapper.map(src -> src.getName(), ProductDTO::setName);
		});
		
		//Product product = productRepo.save(productRequestDto);
		return product;
	}
	*/
	
	
	/* get product by Id */
	public Product getProductById(Long id) {
		return productRepo.findById(id).orElse(null);
	}
	
	
	/* get all products */
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	/* delete product by its id */
	public void deleteProductById(long id) {
		productRepo.deleteById(id);
	}
	
}
