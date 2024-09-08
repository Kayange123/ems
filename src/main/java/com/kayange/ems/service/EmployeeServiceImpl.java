package com.kayange.ems.service;

import com.kayange.ems.dao.EmployeeRepository;
import com.kayange.ems.dto.EmployeeRequest;
import com.kayange.ems.entity.Employee;
import com.kayange.ems.exception.BadRequestException;
import com.kayange.ems.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    @Override
    public void createNewEmployee(EmployeeRequest request) {
        var exists = findFirstByEmail(request.email());
        if(exists != null) {throw new BadRequestException("User with email " + request.email() + " already exists");}
        var employee = Employee.builder().email(request.email())
                .department(request.department()).firstName(request.firstName())
                .lastName(request.lastName()).salary(request.salary()).build();
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void updateEmployee(EmployeeRequest request, Long employeeId) {
        var employee = findEmployeeById(employeeId);
        var emailExists = findFirstByEmail(request.email());
        if(Objects.nonNull(request.firstName())){employee.setFirstName(request.firstName());}
        if(Objects.nonNull(request.lastName())){employee.setLastName(request.lastName());}
        if (Objects.nonNull(request.department())){employee.setDepartment(request.department());}
        if (Objects.nonNull(request.salary())){employee.setSalary(request.salary());}
        if (Objects.nonNull(request.email()) && emailExists ==null){employee.setEmail(request.email());}
        employeeRepository.save(employee);
    }
    @Override
    public Employee findEmployeeById(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(()-> new ResourceNotFoundException("No such employee found for employee id " + employeeId));
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        var employee = findEmployeeById(employeeId);
        employeeRepository.delete(employee);
    }

    private Employee findFirstByEmail(String email) {
        return employeeRepository.findFirstByEmail(email)
                .orElse(null);

    }
    @Override
    public Page<Employee> findAllEmployees(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return  employeeRepository.findAll(pageable);

    }
}
