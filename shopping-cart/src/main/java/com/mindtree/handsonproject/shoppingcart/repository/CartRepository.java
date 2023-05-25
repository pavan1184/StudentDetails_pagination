package com.mindtree.handsonproject.shoppingcart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.handsonproject.shoppingcart.entity.Cart;
import com.mindtree.handsonproject.shoppingcart.entity.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	Optional<Cart> findByUser(User user);
	
}
