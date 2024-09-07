package com.kayange.ems.service;

import com.kayange.ems.dto.EmployeeRequest;
import com.kayange.ems.entity.Employee;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    void createNewEmployee(EmployeeRequest employee);
    void updateEmployee(EmployeeRequest employee, Long employeeId);
    Employee findEmployeeById(Long employeeId);
    void deleteEmployee(Long employeeId);
    Page<Employee> findAllEmployees(int page, int size);
}
