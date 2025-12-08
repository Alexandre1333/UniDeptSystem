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
                newProfessor("Mason","Troy","Assistant Prof", new BigDecimal("76000.00"), d9),
                newProfessor("Alice", "Brown", "Assistant Prof", new BigDecimal("70000.00"), d1),
                newProfessor("Bob", "White", "Associate Prof", new BigDecimal("95000.00"), d2),
                newProfessor("Charlie", "Black", "Professor", new BigDecimal("125000.00"), d3),
                newProfessor("Diana", "Green", "Assistant Prof", new BigDecimal("71000.00"), d4),
                newProfessor("Evan", "Blue", "Associate Prof", new BigDecimal("96000.00"), d5),
                newProfessor("Fiona", "Red", "Professor", new BigDecimal("126000.00"), d6),
                newProfessor("George", "Yellow", "Assistant Prof", new BigDecimal("73000.00"), d7),
                newProfessor("Hannah", "Purple", "Associate Prof", new BigDecimal("97000.00"), d8),
                newProfessor("Ian", "Orange", "Professor", new BigDecimal("127000.00"), d9),
                newProfessor("Julia", "Gray", "Assistant Prof", new BigDecimal("74000.00"), d10),
                newProfessor("Kevin", "Cyan", "Associate Prof", new BigDecimal("99000.00"), d1),
                newProfessor("Laura", "Magenta", "Professor", new BigDecimal("128000.00"), d2),
                newProfessor("Mike", "Lime", "Assistant Prof", new BigDecimal("75000.00"), d3),
                newProfessor("Nina", "Teal", "Associate Prof", new BigDecimal("100000.00"), d4),
                newProfessor("Oscar", "Indigo", "Professor", new BigDecimal("129000.00"), d5),
                newProfessor("Paul", "Violet", "Assistant Prof", new BigDecimal("77000.00"), d6),
                newProfessor("Quinn", "Pink", "Associate Prof", new BigDecimal("101000.00"), d7),
                newProfessor("Rachel", "Silver", "Professor", new BigDecimal("131000.00"), d8),
                newProfessor("Steve", "Gold", "Assistant Prof", new BigDecimal("79000.00"), d9),
                newProfessor("Tina", "Bronze", "Associate Prof", new BigDecimal("102000.00"), d10),
                newProfessor("Uma", "Copper", "Professor", new BigDecimal("132000.00"), d1),
                newProfessor("Victor", "Platinum", "Assistant Prof", new BigDecimal("80000.00"), d2),
                newProfessor("Wendy", "Ruby", "Associate Prof", new BigDecimal("103000.00"), d3),
                newProfessor("Xander", "Emerald", "Professor", new BigDecimal("133000.00"), d4),
                newProfessor("Yara", "Sapphire", "Assistant Prof", new BigDecimal("81000.00"), d5),
                newProfessor("Zack", "Diamond", "Associate Prof", new BigDecimal("104000.00"), d6),
                newProfessor("Adam", "Pearl", "Professor", new BigDecimal("134000.00"), d7),
                newProfessor("Bella", "Opal", "Assistant Prof", new BigDecimal("82000.00"), d8),
                newProfessor("Chris", "Jade", "Associate Prof", new BigDecimal("106000.00"), d9),
                newProfessor("Daisy", "Amber", "Professor", new BigDecimal("136000.00"), d10),
                newProfessor("Ethan", "Topaz", "Assistant Prof", new BigDecimal("83000.00"), d1),
                newProfessor("Faith", "Garnet", "Associate Prof", new BigDecimal("107000.00"), d2),
                newProfessor("Gavin", "Quartz", "Professor", new BigDecimal("137000.00"), d3),
                newProfessor("Holly", "Onyx", "Assistant Prof", new BigDecimal("85000.00"), d4),
                newProfessor("Ivan", "Malachite", "Associate Prof", new BigDecimal("108000.00"), d5),
                newProfessor("Jenny", "Lapis", "Professor", new BigDecimal("138000.00"), d6),
                newProfessor("Kyle", "Agate", "Assistant Prof", new BigDecimal("86000.00"), d7),
                newProfessor("Lily", "Jasper", "Associate Prof", new BigDecimal("109000.00"), d8),
                newProfessor("Matt", "Zircon", "Professor", new BigDecimal("139000.00"), d9),
                newProfessor("Nora", "Coral", "Assistant Prof", new BigDecimal("87000.00"), d10)
        ));
    }

    private Professor newProfessor(String fn, String ln, String title, BigDecimal salary, Department d) {
        Professor p = new Professor(fn, ln, title, salary);
        p.setDepartment(d);
        return p;
    }
}
