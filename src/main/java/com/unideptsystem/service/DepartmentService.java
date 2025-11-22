package com.unideptsystem.service;

import com.unideptsystem.dto.*;
import com.unideptsystem.entity.Department;
import com.unideptsystem.exception.NotFoundException;
import com.unideptsystem.mapper.UniMapper;
import com.unideptsystem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentResponseDTO> getAll() {
        return departmentRepository.findAll().stream().map(UniMapper::toDepartmentResponse).collect(Collectors.toList());
    }

    public DepartmentResponseDTO getOne(Long id) {
        Department d = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department " + id + " not found"));
        return UniMapper.toDepartmentResponse(d);
    }

    public DepartmentResponseDTO create(DepartmentRequestDTO req) {
        Department d = new Department(req.getName(), req.getLocation());
        Department saved = departmentRepository.save(d);
        return UniMapper.toDepartmentResponse(saved);
    }

    public DepartmentResponseDTO update(Long id, DepartmentRequestDTO req) {
        Department d = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department " + id + " not found"));
        d.setName(req.getName());
        d.setLocation(req.getLocation());
        return UniMapper.toDepartmentResponse(departmentRepository.save(d));
    }

    public void delete(Long id) {
        Department d = departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department " + id + " not found"));
        departmentRepository.delete(d);
    }
}
