package com.unideptsystem.bootstrap;

import com.unideptsystem.entity.*;
import com.unideptsystem.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final DepartmentRepository departmentRepository;
    private final ProfessorRepository professorRepository;

    public DataLoader(DepartmentRepository departmentRepository, ProfessorRepository professorRepository) {
        this.departmentRepository = departmentRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() > 0) return;

        Department d1 = new Department("Computer Science","Building A");
        Department d2 = new Department("Mathematics","Building B");
        Department d3 = new Department("Physics","Building C");
        Department d4 = new Department("Chemistry","Building D");
        Department d5 = new Department("Biology","Building E");
        Department d6 = new Department("English","Building F");
        Department d7 = new Department("History","Building G");
        Department d8 = new Department("Economics","Building H");
        Department d9 = new Department("Psychology","Building J");
        Department d10 = new Department("Philosophy","Building K");

        departmentRepository.saveAll(List.of(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10));

        professorRepository.saveAll(List.of(
                newProfessor("Jeanne","Smith","Associate Prof", new BigDecimal("105000.00"), d1),
                newProfessor("John","Doe","Assistant Prof", new BigDecimal("84000.00"), d2),
                newProfessor("Ava","Smith","Professor", new BigDecimal("130000.00"), d1),
                newProfessor("Liam","Johnson","Assistant Prof", new BigDecimal("78000.00"), d3),
                newProfessor("John","Cena","Associate Prof", new BigDecimal("98000.00"), d4),
                newProfessor("Noah","Mitchel","Professor", new BigDecimal("140000.00"), d5),
                newProfessor("Emma","Emmason","Assistant Prof", new BigDecimal("72000.00"), d6),
                newProfessor("Oliver","Moore","Associate Prof", new BigDecimal("92000.00"), d7),
                newProfessor("Whoam","I","Professor", new BigDecimal("135000.00"), d8),
                newProfessor("Mason","Troy","Assistant Prof", new BigDecimal("76000.00"), d9)
        ));
    }

    private Professor newProfessor(String fn, String ln, String title, BigDecimal salary, Department d) {
        Professor p = new Professor(fn, ln, title, salary);
        p.setDepartment(d);
        return p;
    }
}
