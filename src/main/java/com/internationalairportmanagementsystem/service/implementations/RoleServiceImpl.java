package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostRoleDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRoleDto;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.mappers.RoleMapper;
import com.internationalairportmanagementsystem.repository.RoleRepository;
import com.internationalairportmanagementsystem.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private RoleMapper  roleMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(PostRoleDto postRoleDto) {
        Role role = roleMapper.postRole(postRoleDto);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Long roleId, PutRoleDto putRoleDto) {
        Role role = roleMapper.putToRole(putRoleDto);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Boolean existsByRoleName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }

    @Override
    public String deleteById(Long roleId) {
        roleRepository.deleteById(roleId);
        return "Deleted role";
    }

    @Override
    public String deleteAll() {
        roleRepository.deleteAll();
        return "Deleted all roles";
    }
}
