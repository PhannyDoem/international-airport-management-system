package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostEmployeeDto;
import com.internationalairportmanagementsystem.dtos.puts.PutEmployeeDto;
import com.internationalairportmanagementsystem.enetity.Employee;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.mappers.EmployeeMapper;
import com.internationalairportmanagementsystem.repository.EmployeeRepository;
import com.internationalairportmanagementsystem.repository.RoleRepository;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import com.internationalairportmanagementsystem.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;
    private RoleRepository roleRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               EmployeeMapper employeeMapper,
                               RoleRepository roleRepository,
                               UserEntityRepository userEntityRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.roleRepository = roleRepository;

    }
    @Override
    public Employee create(PostEmployeeDto postEmployeeDto) {
        Employee employee = employeeMapper.postToEmployee(postEmployeeDto);
        Role employeeRole = roleRepository.findByRoleName("Employee").get();
        employee.getUserEntity().setRole(employeeRole);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long employeeId, PutEmployeeDto putEmployeeDto) {
        Employee employee = employeeMapper.putToEmployee(putEmployeeDto);
        Role employeeRole = roleRepository.findByRoleName("Employee").get();
        employee.getUserEntity().setRole(employeeRole);
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long employeeId) {
        Optional<Employee> result = employeeRepository.findById(employeeId);
        Employee employee = null;
        if (result.isPresent()) {
            employee = result.get();
        }else {
            throw new RuntimeException("Not Found");
        }
        return employee;
    }

    @Override
    public String deleteById(Long employeeId) {
        employeeRepository.deleteById(employeeId);
        return "Employee with id " + employeeId + " has been deleted";
    }
}
