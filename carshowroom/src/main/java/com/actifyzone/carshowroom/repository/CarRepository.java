package com.actifyzone.carshowroom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actifyzone.carshowroom.entity.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {

}