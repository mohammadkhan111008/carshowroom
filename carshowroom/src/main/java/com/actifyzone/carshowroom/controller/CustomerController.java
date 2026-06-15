package com.actifyzone.carshowroom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.actifyzone.carshowroom.entity.Customer;
import com.actifyzone.carshowroom.entity.User;
import com.actifyzone.carshowroom.repository.CustomerRepository;
import com.actifyzone.carshowroom.repository.UserRepository;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @Autowired
    UserRepository userRepo;

    @PostMapping("/customer/{userId}")
    public Object saveCustomer(@RequestBody Customer customer, @PathVariable int userId){
        User u = userRepo.findById(userId).get(); 
        if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
            return repo.save(customer);
        }
        return "Access Denied! You are not Allowed to access this Restricted Data.";
    }

    @GetMapping("/customer/{userId}")
    public Object getAllCustomers(@PathVariable int userId){
        User u = userRepo.findById(userId).get(); 
        if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
            return repo.findAll();
        }
        return "Access Denied! You are not Allowed to access this Restricted Data.";
    }

    @GetMapping("/customer/details/{customerId}/{userId}")
    public Object getCustomerById(@PathVariable int customerId, @PathVariable int userId) {
        User u = userRepo.findById(userId).get();
        if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
            return repo.findById(customerId).get();
        }
        return "Access Denied! You are not Allowed to access this Restricted Data.";
    }

    @DeleteMapping("/customer/{customerId}/{userId}")
    public String deleteCustomer(@PathVariable int customerId, @PathVariable int userId){
        User u =
        userRepo.findById(userId).get();
        if(u.role.equals("OWNER"))
        {
            repo.deleteById(customerId);
            return "Customer Deleted";
        }
    return "Access Denied";
    }