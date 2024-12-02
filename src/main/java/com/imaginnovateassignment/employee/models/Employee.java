package com.imaginnovateassignment.employee.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @ElementCollection
    private List<String> phoneNumbers;
    private LocalDate doj;
    private double monthlySalary;
}
