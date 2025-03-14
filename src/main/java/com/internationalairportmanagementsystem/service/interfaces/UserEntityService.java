package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.UserEntity;

import java.util.List;

public interface UserEntityService {
    UserEntity create(PostUserDto postUserDto);
    UserEntity update(PutUserDto putUserDto);
    List<UserEntity> findAll();
    Boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
    UserEntity findById(Long userId);
    String deleteById(Long userId);
    String deleteAll();
}
