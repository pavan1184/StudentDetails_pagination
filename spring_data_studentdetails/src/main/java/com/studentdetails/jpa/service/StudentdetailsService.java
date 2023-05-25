package com.studentdetails.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.studentdetails.jpa.entity.Studentdetails;
import com.studentdetails.jpa.repository.StudentdetailsRepository;

import javax.annotation.PostConstruct;
import java.security.PublicKey;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class StudentdetailsService {

    @Autowired
    private StudentdetailsRepository repository;

//    @PostConstruct
//    public void initDB() {
//        List<Product> products = IntStream.rangeClosed(1, 200)
//                .mapToObj(i -> new Product("product" + i, new Random().nextInt(100), new Random().nextInt(50000)))
//                .collect(Collectors.toList());
//        repository.saveAll(products);
//    }


    public List<Studentdetails> findAllStudents() {
        return repository.findAll();
    }


    public List<Studentdetails> findStudentsWithSorting(String field){
        return  repository.findAll(Sort.by(Sort.Direction.ASC,field));
    }


    public Page<Studentdetails> findStudentsWithPagination(int offset,int pageSize){
        Page<Studentdetails> Studentdetails = repository.findAll(PageRequest.of(offset, pageSize));
        return  Studentdetails;
    }

    public Page<Studentdetails> findStudentssWithPaginationAndSorting(int offset,int pageSize,String field){
        Page<Studentdetails> Studentdetails = repository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return  Studentdetails;
    }


	


	




	
	

}
