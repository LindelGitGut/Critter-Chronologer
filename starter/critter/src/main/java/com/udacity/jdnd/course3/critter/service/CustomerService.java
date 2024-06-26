package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {


    @Autowired
    CustomerRepository customerRepository;


    public Customer save(Customer customer) {

        //create new Customer if provided id is null, if so create new Customer
        if (customer.getId() == null) {
            return customerRepository.save(customer);
        }

        //if id provided , find Customer, update
        else {
            Optional<Customer> oldCustomer = customerRepository.findById(customer.getId());
            if (oldCustomer.isPresent()) {
                return customerRepository.save(customer);
            } else {
                throw new IllegalArgumentException("No Customer with provided Id Found for Put Request found!");
            }
        }
    }

    public List<Customer> getAll(){
        return customerRepository.findAll();
    }

    public Customer findById(Long id){
        Optional<Customer> customer= customerRepository.findById(id);
        if (customer.isPresent()){
            return customer.get();
        }

        else {
            throw new IllegalArgumentException("Could not find Customer with provieded ID");
        }
    }

    public Customer getCustomerByPetId(long petId) {
        Optional<Customer> customer = customerRepository.findCustomerByPetId(petId);
        if (customer.isPresent()){return customer.get();}
        else {throw new IllegalArgumentException("Could not find Customer with provided Pet ID: " + petId);}
    }

    public void removeCustomerByID(Long id){
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            Customer oldCustomer = customer.get();
            customerRepository.delete(oldCustomer);
        }
        else {throw new IllegalArgumentException("Could not find Customer with provided id");}

    }
}