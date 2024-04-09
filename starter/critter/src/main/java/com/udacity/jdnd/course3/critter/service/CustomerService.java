package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    //TODO implement
    @Autowired
    CustomerRepository customerRepository;


    public void save(Customer customer) {

        //create new Customer if provided id is null, if so create new Customer
        if (customer.getId() == null) {
            customerRepository.save(customer);
        }

        //if id provided , find Customer, update
        else {
            Optional<Customer> oldCustomer = customerRepository.findById(customer.getId());
            if (oldCustomer.isPresent()) {
                customerRepository.save(customer);
            } else {
                throw new IllegalArgumentException("No Customer with provided Id Found for Put Request found!");
            }
        }
    }
}