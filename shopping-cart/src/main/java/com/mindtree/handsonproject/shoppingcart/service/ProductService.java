package com.mindtree.handsonproject.shoppingcart.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.handsonproject.shoppingcart.entity.Product;
import com.mindtree.handsonproject.shoppingcart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public List<Product> getALLProduct() {
		return productRepository.findAll();
	}

	public List<Product> searchProduct(String keyword) {
		if (keyword != null) {
			return productRepository.search(keyword.toLowerCase());
		}
		return null;
	}

	public Optional<Product> getAProduct(int key) {
		return productRepository.findById(key);
//		try {
//			
//			findById.orElseThrow(() -> new ProductNotFound("No Product Avilable with the id " + key));
//			
//		}catch( ProductNotFound e) {
//			logger.error(e.getMessage());
//		}
//		
//		return findById.get();
	}
}
