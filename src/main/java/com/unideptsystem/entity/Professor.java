package com.unideptsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(length = 50)
    private String title;
    @Column(precision = 12, scale = 2)
    private BigDecimal salary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "department_id",  nullable = false)
    private Department department;

    public Professor() {}
    public Professor(String firstName, String lastName, String title, BigDecimal salary) {
        this.firstName = firstName; this.lastName = lastName; this.title = title; this.salary = salary;
    }

}
