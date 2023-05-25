package com.studentdetails.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentdetails.jpa.dto.APIResponse;
import com.studentdetails.jpa.entity.Studentdetails;
import com.studentdetails.jpa.service.StudentdetailsService;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/studentdetails")
public class PaginationSortingExampleApplication {

    @Autowired
    private StudentdetailsService service;

    @GetMapping
    private APIResponse<List<Studentdetails>> getStudents() {
        List<Studentdetails> allStudents = service.findAllStudents();
        return new APIResponse<>(allStudents.size(), allStudents);
    }

    @GetMapping("/{field}")
    private APIResponse<List<Studentdetails>> getStudentsWithSort(@PathVariable String field) {
        List<Studentdetails> allStudents = service.findStudentsWithSorting(field);
        return new APIResponse<>(allStudents.size(), allStudents);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    private APIResponse<Page<Studentdetails>> getStudentsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Studentdetails> StudentsWithPagination = service.findStudentsWithPagination(offset, pageSize);
        return new APIResponse<>(StudentsWithPagination.getSize(), StudentsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    private APIResponse<Page<Studentdetails>> getStudentsWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize,@PathVariable String field) {
        Page<Studentdetails> StudentsWithPagination = service.findStudentssWithPaginationAndSorting(offset, pageSize, field);
        return new APIResponse<>(StudentsWithPagination.getSize(), StudentsWithPagination);
    }



    public static void main(String[] args) {
        SpringApplication.run(PaginationSortingExampleApplication.class, args);
    }

}
