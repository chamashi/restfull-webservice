package com.example.product.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;


@Service
 public class ProductService {
	
	@Autowired
	private ProductRepository repos;
	
	public List<Product>listAll() {		
		return repos.findAll();
	}
	
	public void save(Product product) {
		repos.save(product);
	}
	
	public Product get(Long id) {
		return repos.findById(id).get();
	}
	
	public void delete(Long id) {
		repos.deleteById(id);
	}
}
