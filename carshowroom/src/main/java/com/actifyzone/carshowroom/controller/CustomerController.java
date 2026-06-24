package com.actifyzone.carshowroom.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.actifyzone.carshowroom.entity.Customer;
import com.actifyzone.carshowroom.entity.User;
import com.actifyzone.carshowroom.repository.CarRepository;
import com.actifyzone.carshowroom.repository.CustomerRepository;
import com.actifyzone.carshowroom.repository.UserRepository;

import org.springframework.web.context.annotation.RequestScope;
import java.time.Duration;




@RestController
public class CustomerController {

    @Autowired
    CustomerRepository repo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    CarRepository carRepo;

    @PostMapping("/customer")
    public Object saveCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                return repo.save(customer);
            }
            return "Access Denied! You are not Allowed to save a Customer.";
        }
    }

    @GetMapping("/customer")
    public Object getAllCustomers(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
            return repo.findAll();
            }
            return "Access Denied! You are not Allowed to access this Restricted Data.";
        }
    }

    @GetMapping("/customer/details/{customerId}")
    public Object getCustomerById(@PathVariable int customerId, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                return repo.findById(customerId).get();
            }
            return "Access Denied! You are not Allowed to access this Restricted Data.";
        }
    }

    @GetMapping("/customer/search/{name}")
    public Object searchByName(@PathVariable String name, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                return repo.findByName(name);
            }
            else{
                return "Access Denied! You are not Allowed to access this Restricted Data.";
            }
        }
    }


    @PutMapping("customer/{customerId}")
    public Object updateCustomer(@RequestBody Customer customer, @PathVariable int customerId, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{     
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                Customer c = repo.findById(customerId).get();
                c.name = customer.name;
                c.email = customer.email;
                c.bookingDate = customer.bookingDate;

                return repo.save(c);
            }
            return "Access Denied! You are not Allowed to update the credentials of any Restricted Data.";
        }
    }

    @GetMapping("/customer/count")
    public Object countCustomer(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                return repo.count();
            }
            else{
                return "Access Denied! You are not Allowed to access this Restricted Data.";
            }
        }
    }
    
    @GetMapping("/car/count")
    public Object countCar(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER") || u.role.equals("MANAGER")){
                return carRepo.count();
            }
            else{
                return "Access Denied! You are not Allowed to access this Restricted Data.";
            }
        }
    }

    @DeleteMapping("/customer/{customerId}")
    public String deleteCustomer(@PathVariable int customerId, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER"))
            {
                repo.deleteById(customerId);
                return "Customer Deleted";
            }
            return "Access Denied! You are not allowed to Delete a Customer.";
        }
    }

    @GetMapping("/dashboard")
    public String getDashboard(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct TOken.";
        }
        if(u.tokenCreatedAt == null){
            return "Please Login Again!";
        }
        long hours = Duration.between(u.tokenCreatedAt, LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again!";
        }
        if(u.role.equals("OWNER")){
            String dashboardData = "Total Number Of Customers : " + repo.count() + 
            "\n" + "Total Number Of Cars : " + carRepo.count() +
            "\n" + "Total Number Of Users : " + userRepo.count();
            return dashboardData;
            
        }
        return "Access Denied! You are not allowed to Access the DashBoard.";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable int userId, @RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        User u = userRepo.findByToken(token);
        if(u == null){
            return "Invalid Token! Enter the correct Token.";
        }
        if(u.tokenCreatedAt == null)
        {
            return "Please Login Again";
        }
        long hours = Duration.between(u.tokenCreatedAt,LocalDateTime.now()).toHours();
        if(hours > 24){
            return "Token Expired! Please Login Again.";
        }
        else{
            if(u.role.equals("OWNER"))
            {
                userRepo.deleteById(userId);
                return  "Deleted";
            }
            return "Access Denied! You are not allowed to Delete a Customer.";
        }
    }
}