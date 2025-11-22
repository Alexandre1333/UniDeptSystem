package com.unideptsystem.repository;

import com.unideptsystem.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    List<Professor> findByDepartmentId(Long departmentId);
}
