package com.udacity.jdnd.course3.critter.data;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;





}
