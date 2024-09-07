package com.kayange.ems.dao;

import com.kayange.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
        From Employee e
        WHERE e.email = :email
    """)
    Optional<Employee> findFirstByEmail(@Param("email") String email);
}
