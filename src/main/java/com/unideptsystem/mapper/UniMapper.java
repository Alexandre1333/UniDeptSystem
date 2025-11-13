package com.unideptsystem.mapper;

import com.unideptsystem.dto.*;
import com.unideptsystem.entity.*;

public class UniMapper {
    public static DepartmentResponseDTO toDepartmentResponse(Department d) {
        DepartmentResponseDTO r = new DepartmentResponseDTO();
        r.setId(d.getId());
        r.setName(d.getName());
        r.setLocation(d.getLocation());
        return r;
    }

    public static ProfessorResponseDTO toProfessorResponse(Professor p) {
        ProfessorResponseDTO r = new ProfessorResponseDTO();
        r.setId(p.getId());
        r.setFirstName(p.getFirstName());
        r.setLastName(p.getLastName());
        r.setTitle(p.getTitle());
        r.setSalary(p.getSalary());
        if (p.getDepartment() != null) {
            r.setDepartmentId(p.getDepartment().getId());
            r.setDepartmentName(p.getDepartment().getName());
        }
        return r;
    }
}
