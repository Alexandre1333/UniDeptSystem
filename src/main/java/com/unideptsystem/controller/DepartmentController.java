package com.unideptsystem.controller;

import com.unideptsystem.dto.*;
import com.unideptsystem.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) { this.departmentService = departmentService; }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() { return ResponseEntity.ok(departmentService.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getOne(@PathVariable Long id) { return ResponseEntity.ok(departmentService.getOne(id)); }



    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> create(@Valid @RequestBody DepartmentRequestDTO req) {
        DepartmentResponseDTO created = departmentService.create(req);
        return ResponseEntity.created(URI.create("/api/departments/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> update(@PathVariable Long id, @Valid @RequestBody DepartmentRequestDTO req) {
        return ResponseEntity.ok(departmentService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
