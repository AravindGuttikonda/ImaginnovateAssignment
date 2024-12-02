package com.imaginnovateassignment.employee.controlleradvice;

import com.imaginnovateassignment.employee.dtos.EmployeeResponseDTO;
import com.imaginnovateassignment.employee.dtos.TaxResponseDTO;
import com.imaginnovateassignment.employee.exceptions.EmployeeNotFoundException;
import com.imaginnovateassignment.employee.exceptions.EmployeeNotSavedException;
import com.imaginnovateassignment.employee.exceptions.MandatoryEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeControllerAdvice {
    @ExceptionHandler(EmployeeNotSavedException.class)
    public ResponseEntity<EmployeeResponseDTO> handleEmployeeNotSavedException(){
        return new ResponseEntity<>(new EmployeeResponseDTO(null, "Employee not saved"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<TaxResponseDTO> handleEmployeeNotFoundException(){
        return new ResponseEntity<>(new TaxResponseDTO(null, null, null, 0, 0, 0), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MandatoryEmailException.class)
    public ResponseEntity<EmployeeResponseDTO> handleMandatoryEmailException(){
        return new ResponseEntity<>(new EmployeeResponseDTO(null, "Email is mandatory"), HttpStatus.NOT_ACCEPTABLE);
    }

    //Need to handle the remaining exceptions here
}
