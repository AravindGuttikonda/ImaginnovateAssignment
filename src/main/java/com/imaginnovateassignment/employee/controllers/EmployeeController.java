package com.imaginnovateassignment.employee.controllers;

import com.imaginnovateassignment.employee.dtos.EmployeeResponseDTO;
import com.imaginnovateassignment.employee.dtos.EmployeeRequestDTO;
import com.imaginnovateassignment.employee.dtos.TaxResponseDTO;
import com.imaginnovateassignment.employee.exceptions.*;
import com.imaginnovateassignment.employee.models.Employee;
import com.imaginnovateassignment.employee.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponseDTO> addEmployee(@RequestBody EmployeeRequestDTO employeeRequestDTO) throws EmployeeNotSavedException, MandatoryEmailException, MandatoryEmployeeIdException, InvalidPhoneNumberException, MandatoryDOJException, MandatoryPhoneNumberException, MandatoryOrNonZeroMonthlySalaryException, MandatoryFirstNameException, MandatoryLastNameException {
        Employee employee = employeeService.addEmployee(employeeRequestDTO);
        return new ResponseEntity<>(new EmployeeResponseDTO(employee, "Employee saved successfully"), HttpStatus.OK);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<TaxResponseDTO> getTaxDetails(@PathVariable("id") Long id) throws EmployeeNotFoundException {
        TaxResponseDTO taxResponseDTO = employeeService.getTaxDetails(id);
        return new ResponseEntity<>(taxResponseDTO, HttpStatus.OK);
    }
}
