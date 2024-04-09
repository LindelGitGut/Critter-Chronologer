package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface CustomerRepository extends JpaRepository<Customer, Long> {
    //TODO check if custom Actions are needed and implement them via MEthod Name Synatx or via Query
}
