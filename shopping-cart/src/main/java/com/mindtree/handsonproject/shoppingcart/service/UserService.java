package com.mindtree.handsonproject.shoppingcart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.handsonproject.shoppingcart.entity.User;
import com.mindtree.handsonproject.shoppingcart.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllActiveUsers(){
		return userRepository.findAll();
	}
	

}
