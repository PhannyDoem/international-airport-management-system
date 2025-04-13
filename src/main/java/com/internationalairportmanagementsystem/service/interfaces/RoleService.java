package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Role;

import java.util.List;

public interface RoleService {
    Role create(PostRoleDto postRoleDto);
    Role update(Long roleId, PutRoleDto  putRoleDto);
    List<Role> findAll();
    Role findById(Long roleId);
    Boolean existsByRoleName(String roleName);
    String deleteById(Long roleId);
    String deleteAll();
}
