package com.unideptsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProfessorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private BigDecimal salary;
    private Long departmentId;
    private String departmentName;

}
