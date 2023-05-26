package com.example.product.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Customer;

import com.example.product.repository.CustomerRepository;


@Service
public class CustomerService {
	@Autowired
	private CustomerRepository repo;
	
	public List<Customer>listAll() {		
		return repo.findAll();
	}
	
	public Customer save(Customer customer) {
		return repo.save(customer);
	}
	
	public Customer get(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
}
