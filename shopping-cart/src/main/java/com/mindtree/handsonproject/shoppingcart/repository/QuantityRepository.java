package com.mindtree.handsonproject.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.handsonproject.shoppingcart.entity.Cart;
import com.mindtree.handsonproject.shoppingcart.entity.Product;
import com.mindtree.handsonproject.shoppingcart.entity.Quantity;

public interface QuantityRepository extends JpaRepository<Quantity, Integer>{
	
	Optional<List<Quantity>> findByCart(Cart cart);
	Optional<Quantity> findByProduct(Product product);

}
