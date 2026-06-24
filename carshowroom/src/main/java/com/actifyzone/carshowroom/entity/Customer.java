package com.actifyzone.carshowroom.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String name;

    public String email;

    public String bookingDate;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Car> cars;
    
}