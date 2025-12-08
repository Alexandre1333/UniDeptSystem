package com.unideptsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String name;
    @Column(length = 100)
    private String location;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Professor> professors = new ArrayList<>();

    public Department() {}
    public Department(String name, String location) { this.name = name; this.location = location; }

    public void addProfessor(Professor p) { professors.add(p); p.setDepartment(this); }
    public void removeProfessor(Professor p) { professors.remove(p); p.setDepartment(null); }
}
