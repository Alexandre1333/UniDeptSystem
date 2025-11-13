package com.unideptsystem.service;

import com.unideptsystem.dto.*;
import com.unideptsystem.entity.*;
import com.unideptsystem.exception.NotFoundException;
import com.unideptsystem.mapper.UniMapper;
import com.unideptsystem.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;

    public ProfessorService(ProfessorRepository professorRepository, DepartmentRepository departmentRepository) {
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<ProfessorResponseDTO> getAll() {
        return professorRepository.findAll().stream().map(UniMapper::toProfessorResponse).collect(Collectors.toList());
    }

    public ProfessorResponseDTO getOne(Long id) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new NotFoundException("Professor " + id + " not found"));
        return UniMapper.toProfessorResponse(p);
    }

    public ProfessorResponseDTO create(ProfessorRequestDTO req) {
        Professor p = new Professor(req.getFirstName(), req.getLastName(), req.getTitle(), Optional.ofNullable(req.getSalary()).orElse(BigDecimal.ZERO));
        if (req.getDepartmentId() != null) {
            Department d = departmentRepository.findById(req.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department " + req.getDepartmentId() + " not found"));
            p.setDepartment(d);
        }
        Professor saved = professorRepository.save(p);
        return UniMapper.toProfessorResponse(saved);
    }

    public ProfessorResponseDTO update(Long id, ProfessorRequestDTO req) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new NotFoundException("Professor " + id + " not found"));
        p.setFirstName(req.getFirstName());
        p.setLastName(req.getLastName());
        p.setTitle(req.getTitle());
        p.setSalary(Optional.ofNullable(req.getSalary()).orElse(BigDecimal.ZERO));
        if (req.getDepartmentId() != null) {
            Department d = departmentRepository.findById(req.getDepartmentId()).orElseThrow(() -> new NotFoundException("Department " + req.getDepartmentId() + " not found"));
            p.setDepartment(d);
        } else {
            p.setDepartment(null);
        }
        return UniMapper.toProfessorResponse(professorRepository.save(p));
    }

    public void delete(Long id) {
        Professor p = professorRepository.findById(id).orElseThrow(() -> new NotFoundException("Professor " + id + " not found"));
        professorRepository.delete(p);
    }

    public List<ProfessorResponseDTO> getByDepartment(Long departmentId) {
        return professorRepository.findByDepartmentId(departmentId).stream().map(UniMapper::toProfessorResponse).collect(Collectors.toList());
    }
}
