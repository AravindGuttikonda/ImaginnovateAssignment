package com.imaginnovateassignment.employee.dtos;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class EmployeeRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> phoneNumbers;
    private LocalDate doj;
    private double monthlySalary;
}
