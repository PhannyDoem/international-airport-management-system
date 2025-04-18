package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.Role;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity postToUser(PostUserDto  postUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(postUserDto.username());
        user.setPassword(passwordEncoder.encode(postUserDto.password()));
        user.setUserId(0L);
        return user;

    }

    public UserEntity putToUser(PutUserDto putUserDto) {
        UserEntity user = new UserEntity();
        user.setUsername(putUserDto.username());
        user.setPassword(passwordEncoder.encode(putUserDto.password()));
        user.setUserId(0L);
        return user;

    }
}
