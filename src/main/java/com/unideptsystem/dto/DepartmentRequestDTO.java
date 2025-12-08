package com.unideptsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DepartmentRequestDTO {
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @Size(min = 1, max = 30)
    private String location;

}