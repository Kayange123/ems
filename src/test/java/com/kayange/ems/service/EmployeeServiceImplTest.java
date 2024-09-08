package com.kayange.ems.service;

import com.kayange.ems.dao.EmployeeRepository;
import com.kayange.ems.dto.EmployeeRequest;
import com.kayange.ems.entity.Employee;
import com.kayange.ems.exception.BadRequestException;
import com.kayange.ems.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        ///Given
        //-->Create request object
        var employeeRequest = new EmployeeRequest("Ayubu", "kayange", "ayubu@kayange.com", "CoICT", 200.8);
        //--> Create employee object
        var employee = Employee.builder().salary(employeeRequest.salary()).firstName(employeeRequest.firstName())
                .lastName(employeeRequest.lastName()).email(employeeRequest.email()).department(employeeRequest.department()).build();
        //When
        //--> Attempt to fire method
        testService.createNewEmployee(employeeRequest);
        //Then
        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).saveAndFlush(captor.capture());
        Employee value = captor.getValue();
        assertThat(employee).isEqualTo(value);
    }

    @Test
    void shouldUpdateEmployeeWhenIdExists() {
        //Given
        long employeeId = 1L;
        var employeeRequest = new EmployeeRequest("Ayubu", "kayange", "ayubu@kayange.com", "CoICT", 200.8);
        //--> Create employee object
        var employee = Employee.builder().salary(employeeRequest.salary()).firstName(employeeRequest.firstName())
                .lastName(employeeRequest.lastName()).email(employeeRequest.email()).department(employeeRequest.department()).build();

        //When
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        testService.updateEmployee(employeeRequest, employeeId);

        //Then
        verify(employeeRepository).save(employee);
    }

    @Test
    void willThrowExceptionWhenEmailExists() {
        //Given
        var employeeRequest = new EmployeeRequest("Ayubu", "kayange", "ayubu@kayange.com", "CoICT", 200.8);
        //--> Create employee object
        var employee = Employee.builder().salary(employeeRequest.salary()).firstName(employeeRequest.firstName())
                .lastName(employeeRequest.lastName()).email(employeeRequest.email()).department(employeeRequest.department()).build();
        //When
        BDDMockito.given(employeeRepository.findFirstByEmail(employee.getEmail())).willReturn(Optional.of(employee));
        assertThatThrownBy(()-> testService.createNewEmployee(employeeRequest))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("User with email " + employeeRequest.email() + " already exists");
        }

    @Test
    void shouldThrowExceptionWhenEmployeeByIdNotFound() {
        //Given
        long employeeId = 1;
        //When
        assertThatThrownBy(()-> testService.findEmployeeById(employeeId))
                .isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("No such employee found for employee id " + employeeId);
        //Then
        verify(employeeRepository).findById(employeeId);
    }

    @Test
    void shouldFindEmployeeById(){
        //Given
        long employeeId = 2L;
        Employee employee = new Employee(employeeId, "John","Doe","john@doe.com","ICT", 100.0, null, null);

        //When
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        var foundEmployee = testService.findEmployeeById(employeeId);

        //Then
        assertNotNull(foundEmployee);
        assertEquals(employeeId, foundEmployee.getId());
        assertEquals("John", employee.getFirstName());
    }

    @Test
    void shouldDeleteEmployeeWhenIdExists() {
        //Given
        long employeeId = 52L;
        Employee employee = new Employee(employeeId, "John","Doe","john@doe.com","ICT", 100.0, null, null);

        //When
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        var foundEmployee = testService.findEmployeeById(employeeId);
        doNothing().when(employeeRepository).delete(foundEmployee);
        testService.deleteEmployee(foundEmployee.getId());
        //Then
        verify(employeeRepository, times(1)).delete(foundEmployee);

    }

    @Test
    void ShouldFindAllEmployees() {
        //Given
        int page = 0, size =10;
        var pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        //When
        testService.findAllEmployees(page, size);
        //Then
        verify(employeeRepository).findAll(pageable);
    }
}