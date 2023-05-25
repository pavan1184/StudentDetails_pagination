package com.mindtree.handsonproject.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.handsonproject.shoppingcart.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
