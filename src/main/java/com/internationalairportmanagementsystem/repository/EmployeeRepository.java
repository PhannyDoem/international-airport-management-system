package com.internationalairportmanagementsystem.repository;

import com.internationalairportmanagementsystem.enetity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e join fetch e.userEntity u where u.userId = :userId")
    List<Employee> findUserEntityId(@Param("user") Long userId);
}
