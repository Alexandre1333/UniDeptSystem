package com.unideptsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DepartmentRequestDTO {
    @NotBlank
    @Size(min = 1, max = 30)
    private String name;
    @Size(min = 1, max = 30)
    private String location;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}
