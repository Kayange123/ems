package com.kayange.ems.dto;

import com.kayange.ems.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private Double salary;
    private String createdAt;

    public static EmployeeResponse create(Employee emp){
        return EmployeeResponse.builder()
                .id(emp.getId())
                .department(emp.getDepartment())
                .createdAt(emp.getCreatedAt().toString())
                .salary(emp.getSalary())
                .firstName(emp.getFirstName())
                .lastName(emp.getLastName())
                .email(emp.getEmail())
                .build();
    }
}
