package com.imaginnovateassignment.employee.dtos;

import com.imaginnovateassignment.employee.models.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeResponseDTO {
    private Employee employee;
    private String message;
}
