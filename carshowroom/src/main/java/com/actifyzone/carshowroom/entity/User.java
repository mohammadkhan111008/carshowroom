package com.actifyzone.carshowroom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public String username;

    public String password;

    public String role;

    public String token;

    public LocalDateTime tokenCreatedAt;
    
}