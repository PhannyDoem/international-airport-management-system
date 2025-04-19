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
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final RoleRepository roleRepository;
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
        Role employeeRole = roleRepository.findByRoleName("Employee").orElseThrow();
        employee.getUserEntity().setRole(employeeRole);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long employeeId, PutEmployeeDto putEmployeeDto) {
        if (employeeId != null){
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeId)
                    .stream().findFirst()
                    .map(
                            updated -> {
                                updated.setRole(putEmployeeDto.role());
                                updated.setContactInfo(putEmployeeDto.contactInfo());
                                updated.setName(putEmployeeDto.name());
                                updated.setUserEntity(putEmployeeDto.userEntity());
                                return employeeRepository.save(updated);
                            }
                    );
        }
        return employeeRepository.findById(Objects.requireNonNull(employeeId))
                .orElseThrow(() -> new RuntimeException("Update Employee with Id"));
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
