package com.unideptsystem.controller;

import com.unideptsystem.dto.*;
import com.unideptsystem.service.ProfessorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/professors")
public class ProfessorController {
    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) { this.professorService = professorService; }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> getAll() { return ResponseEntity.ok(professorService.getAll()); }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> getOne(@PathVariable Long id) { return ResponseEntity.ok(professorService.getOne(id)); }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<ProfessorResponseDTO>> getByDepartment(@PathVariable Long deptId) {
        return ResponseEntity.ok(professorService.getByDepartment(deptId));
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> create(@Valid @RequestBody ProfessorRequestDTO req) {
        ProfessorResponseDTO created = professorService.create(req);
        return ResponseEntity.created(URI.create("/api/professors/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO req) {
        return ResponseEntity.ok(professorService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
