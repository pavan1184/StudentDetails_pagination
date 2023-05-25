package com.studentdetails.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENTDETAILS_TBL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Studentdetails {
    @Id
    @GeneratedValue
    private  int id;
    private String name;
    private int totalmarks;


    public Studentdetails(String name, int totalmarks) {
        this.name = name;
        this.totalmarks = totalmarks;
 
    }
}
