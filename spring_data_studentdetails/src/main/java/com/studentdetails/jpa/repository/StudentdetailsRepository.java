package com.studentdetails.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentdetails.jpa.entity.Studentdetails;

public interface StudentdetailsRepository extends JpaRepository<Studentdetails,Integer> {
}
