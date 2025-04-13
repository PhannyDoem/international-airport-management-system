package com.internationalairportmanagementsystem.mappers;


import com.internationalairportmanagementsystem.dtos.posts.PostEmployeeDto;
import com.internationalairportmanagementsystem.dtos.puts.PutEmployeeDto;
import com.internationalairportmanagementsystem.enetity.Employee;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    private PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Employee postToEmployee(PostEmployeeDto postEmployeeDto){
        Employee employee = new Employee(
                postEmployeeDto.name(),
                postEmployeeDto.role(),
                postEmployeeDto.contactInfo(),
                postEmployeeDto.userEntity()
        );
        employee.setEmployeeId(0L);
        UserEntity user = new UserEntity(postEmployeeDto.userEntity().getUsername(),
                passwordEncoder.encode(postEmployeeDto.userEntity().getPassword()));
        employee.setUserEntity(user);
        return employee;
    }

    public Employee putToEmployee(PutEmployeeDto putEmployeeDto){
        Employee employee = new Employee(
                putEmployeeDto.name(),
                putEmployeeDto.role(),
                putEmployeeDto.contactInfo(),
                putEmployeeDto.userEntity()
        );
        employee.setEmployeeId(0L);
        UserEntity user = new UserEntity(putEmployeeDto.userEntity().getUsername(),
         passwordEncoder.encode(putEmployeeDto.userEntity().getPassword()));
        employee.setUserEntity(user);
        return employee;
    }
}
