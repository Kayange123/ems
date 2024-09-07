package com.kayange.ems.service;

import com.kayange.ems.dao.EmployeeRepository;
import com.kayange.ems.dto.EmployeeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService testService;

    @BeforeEach
    void SetUp() throws Exception {
        testService = new EmployeeServiceImpl(employeeRepository);
    }

    @Test
    void shouldCreateNewEmployee() {
        //Given
        var employeeRequest = new EmployeeRequest("Ayubu", "kayange", "ayubu@kayange.com", "CoICT", 200.8);
        //When
        testService.createNewEmployee(employeeRequest);
        //Then
        ArgumentCaptor<EmployeeRequest> captor = ArgumentCaptor.forClass(EmployeeRequest.class);
        verify(testService).createNewEmployee(captor.capture());
        EmployeeRequest value = captor.getValue();
        assertThat(employeeRequest).isEqualTo(value);
    }

    @Test
    void shouldUpdateEmployee() {

    }

    @Test
    void shouldFindEmployeeById() {

    }

    @Test
    void shouldDeleteEmployee() {
    }

    @Test
    void ShouldFindAllEmployees() {
        //Given
        int page = 0, size =10;
        var pageable = PageRequest.of(page, size);
        //When
        testService.findAllEmployees(page, size);
        //Then
        verify(employeeRepository).findAll(pageable);
    }
}