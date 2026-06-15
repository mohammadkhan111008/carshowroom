package com.actifyzone.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actifyzone.carshowroom.entity.Customer;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {

}