package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostEmployeeDto;
import com.internationalairportmanagementsystem.dtos.puts.PutEmployeeDto;
import com.internationalairportmanagementsystem.enetity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee create(PostEmployeeDto  postEmployeeDto);

    Employee update(PutEmployeeDto putEmployeeDto);

    List<Employee> findAll();

    Employee findById(Long employeeId);

    String deleteById(Long employeeId);
}
