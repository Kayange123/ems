package com.kayange.ems.api;

import com.kayange.ems.dto.ApiResponse;
import com.kayange.ems.dto.EmployeeRequest;
import com.kayange.ems.dto.EmployeeResponse;
import com.kayange.ems.dto.PageResponse;
import com.kayange.ems.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees") //Accessible via [server]/api/employees - /api is configured in context path
@RequiredArgsConstructor
@Tag(name = "Employees", description = "The employees related API endpoints")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create a new employee")
    public ResponseEntity<ApiResponse<?>> createNewEmployee(@RequestBody @Valid EmployeeRequest request){
        employeeService.createNewEmployee(request);
        var response = ApiResponse.builder()
                .status(HttpStatus.CREATED.name())
                .code(HttpStatus.CREATED.value())
                .success(true)
                .message("Employee created successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all employees by pagination")
    public ResponseEntity<ApiResponse<?>> findAllEmployees(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size){
        var employees = employeeService.findAllEmployees(page, size);
        var response = ApiResponse.builder()
                .status(HttpStatus.OK.name())
                .code(HttpStatus.OK.value())
                .success(true)
                .data(employees)
                .message("Employees retrieved successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<ApiResponse<?>> findEmployeeById(@Valid @PathVariable("id") @Positive Long id){
        var employee = employeeService.findEmployeeById(id);
        var response = ApiResponse.builder()
                .status(HttpStatus.OK.name())
                .code(HttpStatus.OK.value())
                .success(true)
                .data(EmployeeResponse.create(employee))
                .message("Employee retrieved successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee details")
    public ResponseEntity<ApiResponse<?>> updateEmployeeDetails(@RequestBody @Valid EmployeeRequest request ,@Valid @PathVariable("id") @Positive Long id){
        employeeService.updateEmployee(request, id);
        var response = ApiResponse.builder()
                .status(HttpStatus.OK.name())
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Employee updated successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employee details")
    public ResponseEntity<ApiResponse<?>> deleteEmployeeDetails(@Valid @PathVariable("id") @Positive Long id){
        employeeService.deleteEmployee(id);
        var response = ApiResponse.builder()
                .status(HttpStatus.OK.name())
                .code(HttpStatus.OK.value())
                .success(true)
                .message("Employee deleted successfully")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
