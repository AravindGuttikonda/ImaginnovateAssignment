package com.imaginnovateassignment.employee.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaxResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private double yearlySalary;
    private double taxAmount;
    private double cessAmount;
}
