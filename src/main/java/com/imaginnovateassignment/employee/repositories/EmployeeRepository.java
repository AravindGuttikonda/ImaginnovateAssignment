package com.imaginnovateassignment.employee.repositories;

import com.imaginnovateassignment.employee.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
