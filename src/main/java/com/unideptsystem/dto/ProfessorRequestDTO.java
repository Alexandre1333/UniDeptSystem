package com.unideptsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ProfessorRequestDTO {
    @NotBlank @Size(max = 50)
    private String firstName;
    @NotBlank @Size(max = 50)
    private String lastName;
    @Size(max = 50)
    private String title;
    @PositiveOrZero
    private BigDecimal salary;
    @NotNull(message = "departmentId is required")
    private Long departmentId;

}
