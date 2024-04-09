package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.data.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface CustomerRepository extends JpaRepository<Customer, Long> {
    //TODO check if custom Actions are needed and implement them via MEthod Name Synatx or via Query

    //Custom Query to Retrive Customer which has pet with id
    @Query("SELECT c FROM Customer c JOIN c.pets p WHERE p.id = :petId")
    Optional<Customer> findCustomerByPetId(Long petId);
}
