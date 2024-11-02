package com.tecnopar.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "customer")
public class CustomerEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 100)
    private String name;

    @Column(nullable = false,length = 15)
    private  String phone;

    @Email
    private String email;

    @Column(length = 150)
    private String address;

    @Positive
    private  Long age;

}
