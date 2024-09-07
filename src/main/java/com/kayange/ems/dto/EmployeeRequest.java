package com.kayange.ems.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record EmployeeRequest(
      @NotEmpty(message = "FirstName should not be empty or null") String firstName,
      @NotEmpty(message = "LastName should not be empty or null")  String lastName,
      @Email(message = "Invalid Email provided") String email,
      @NotEmpty(message = "Department should not be empty or null")  String department,
      @Positive(message = "Salary should be provided") Double salary
) {
}
