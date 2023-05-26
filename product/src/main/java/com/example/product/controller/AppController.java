package com.example.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.product.model.Customer;
import com.example.product.model.Product;
import com.example.product.service.CustomerService;
import com.example.product.service.ProductService;

@Controller
public class AppController {
	
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Customer> listCustomer = service.listAll();
		model.addAttribute("listCustomer", listCustomer);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewCustomerForm(Model model) {
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		return "new_customer";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		service.save(customer);
		
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditCustomerForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("edit_customer");
		
		Customer customer = service.get(id);
		mav.addObject("customer", customer);
		
		return mav;
	}	
	
	@RequestMapping("/delete/{id}")
	public String deleteCustomer(@PathVariable(name = "id") Long id) {
		service.delete(id);
		
		return "redirect:/";
	}
	
	@Autowired
	private ProductService pservice;
	
	@RequestMapping("/indexp")
	public String viewProductPage(Model model) {
		List<Product> listProduct = pservice.listAll();
		model.addAttribute("listProduct", listProduct);
		
		return "indexp";
	}
	
	@RequestMapping("/newp")
	public String showNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		pservice.save(product);
		
		return "redirect:/indexp";
	}
	
	@RequestMapping("/update/{id}")
	public ModelAndView showEditProductForm(@PathVariable(name = "id") Long id) {
		ModelAndView mav = new ModelAndView("update_product");
		
		Product product = pservice.get(id);
		mav.addObject("product", product);
		
		return mav;
	}	
	
	@RequestMapping("/remove/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		pservice.delete(id);
		
		return "redirect:/indexp";
	}
	
	@GetMapping("/login")
	public String loginpage() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	
}
