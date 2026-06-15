package com.actifyzone.carshowroom.entity;

import jakarta.persistence.*;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String model;

    public String company;
}