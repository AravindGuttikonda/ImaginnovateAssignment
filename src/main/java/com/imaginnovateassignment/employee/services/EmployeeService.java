package com.imaginnovateassignment.employee.services;

import com.imaginnovateassignment.employee.dtos.EmployeeRequestDTO;
import com.imaginnovateassignment.employee.dtos.TaxResponseDTO;
import com.imaginnovateassignment.employee.exceptions.*;
import com.imaginnovateassignment.employee.models.Employee;
import com.imaginnovateassignment.employee.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(EmployeeRequestDTO employeeRequestDTO) throws EmployeeNotSavedException, MandatoryEmployeeIdException, MandatoryFirstNameException, MandatoryLastNameException, MandatoryEmailException, MandatoryDOJException, MandatoryOrNonZeroMonthlySalaryException, MandatoryPhoneNumberException, InvalidPhoneNumberException {
        if(employeeRequestDTO.getId() == null){
            throw new MandatoryEmployeeIdException();
        }
        if(employeeRequestDTO.getFirstName() == null){
            throw new MandatoryFirstNameException();
        }
        if(employeeRequestDTO.getLastName() == null){
            throw new MandatoryLastNameException();
        }
        if(employeeRequestDTO.getEmail() == null){
            throw new MandatoryEmailException();
        }
        if(employeeRequestDTO.getDoj() == null){
            throw new MandatoryDOJException();
        }
        if(employeeRequestDTO.getMonthlySalary() == 0){
            throw new MandatoryOrNonZeroMonthlySalaryException();
        }
        if(employeeRequestDTO.getPhoneNumbers() == null){
            throw new MandatoryPhoneNumberException();
        }
        List<String> invalidPhoneNumbers = employeeRequestDTO.getPhoneNumbers().stream().filter(phoneNumber -> phoneNumber.length() != 10).collect(Collectors.toList());
        if(invalidPhoneNumbers.size() != 0){
            throw new InvalidPhoneNumberException();
        }
        Employee employee = new Employee();
        employee.setDoj(employeeRequestDTO.getDoj());
        employee.setId(employeeRequestDTO.getId());
        employee.setEmail(employeeRequestDTO.getEmail());
        employee.setLastName(employeeRequestDTO.getLastName());
        employee.setFirstName(employeeRequestDTO.getFirstName());
        employee.setMonthlySalary(employeeRequestDTO.getMonthlySalary());
        employee.setPhoneNumbers(employeeRequestDTO.getPhoneNumbers());
        Employee savedEmployee = employeeRepository.save(employee);
        if(savedEmployee == null){
            throw new EmployeeNotSavedException();
        }
        return savedEmployee;
    }

    public TaxResponseDTO getTaxDetails(Long id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            throw new EmployeeNotFoundException();
        }
        Employee employee = employeeOptional.get();
        double monthlySalary = employee.getMonthlySalary();
        double yearlySalary = 0;
        LocalDate doj = employee.getDoj();
        if(doj.isBefore(LocalDate.of(2024, Month.APRIL, 1))){
            yearlySalary = monthlySalary*12;
        }else{
            int monthValue = doj.getMonthValue();
            if(monthValue == 1 || monthValue == 2 || monthValue == 3){
                yearlySalary = monthlySalary*(3 - monthValue);
            }else{
                yearlySalary = monthlySalary*(15 - monthValue);
            }
            yearlySalary += (doj.lengthOfMonth() - doj.getDayOfMonth() + 1)*(monthlySalary/30);
        }
        double tax = 0;
        if(yearlySalary > 1000000){
            tax = 0.2*(yearlySalary - 1000000) + 0.1*(1000000 - 500000) + 0.05*(500000 - 250000);
        }else if(yearlySalary > 500000 && yearlySalary <= 1000000){
            tax = 0.1*(yearlySalary - 500000) + 0.05*(500000 - 250000);
        }else if(yearlySalary > 250000 && yearlySalary <= 500000){
            tax = 0.05*(yearlySalary - 250000);
        }
        double cess = 0;
        if(yearlySalary > 2500000){
            cess = 0.02*(yearlySalary - 2500000);
        }
        TaxResponseDTO taxResponseDTO = new TaxResponseDTO();
        taxResponseDTO.setId(id);
        taxResponseDTO.setFirstName(employee.getFirstName());
        taxResponseDTO.setLastName(employee.getLastName());
        taxResponseDTO.setYearlySalary(yearlySalary);
        taxResponseDTO.setTaxAmount(tax);
        taxResponseDTO.setCessAmount(cess);
        return taxResponseDTO;
    }
}
