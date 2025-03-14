package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.mappers.UserMapper;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private UserEntityRepository userEntityRepository;
    private UserMapper userMapper;
    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, UserMapper userMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserEntity create(PostUserDto postUserDto) {
        UserEntity user = userMapper.postToUser(postUserDto);
        return userEntityRepository.save(user);
    }

    @Override
    public UserEntity update(PutUserDto putUserDto) {
        UserEntity user = userMapper.putToUser(putUserDto);
        return userEntityRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userEntityRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserEntity findById(Long userId) {
        return userEntityRepository.findById(userId).orElse(null);
    }

    @Override
    public String deleteById(Long userId) {
        userEntityRepository.deleteById(userId);
        return "Deleted user";
    }

    @Override
    public String deleteAll() {
        userEntityRepository.deleteAll();
        return "DELETED ALL USERS";
    }
}
