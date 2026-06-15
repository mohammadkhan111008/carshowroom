package com.actifyzone.carshowroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.actifyzone.carshowroom.entity.Customer;
import com.actifyzone.carshowroom.repository.CustomerRepository;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @PostMapping("/customer")
    public Customer saveCustomer(@RequestBody Customer customer) {

        return repo.save(customer);
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers() {

        return repo.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable int id) {

        return repo.findById(id).get();
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable int id) {

        repo.deleteById(id);

        return "Customer Deleted Successfully";
    }
}