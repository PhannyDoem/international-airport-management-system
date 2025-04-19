package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Role;
import org.springframework.stereotype.Service;

@Service
public class RoleMapper {

    public Role postRole(PostRoleDto  postRoleDto) {
        Role role = new Role(postRoleDto.roleName());
        role.setRoleId(0L);
        return role;
    }

    public Role putToRole(PutRoleDto putRoleDto) {
        Role role = new Role(putRoleDto.roleName());
        role.setRoleId(0L);
        return role;
    }
}
