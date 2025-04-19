package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostUserDto;
import com.internationalairportmanagementsystem.dtos.puts.PutUserDto;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import com.internationalairportmanagementsystem.mappers.UserMapper;
import com.internationalairportmanagementsystem.repository.UserEntityRepository;
import com.internationalairportmanagementsystem.service.interfaces.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserEntityServiceImpl implements UserEntityService {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserEntityServiceImpl(UserEntityRepository userEntityRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity create(PostUserDto postUserDto) {
        UserEntity user = userMapper.postToUser(postUserDto);
        return userEntityRepository.save(user);
    }

    @Override
    public UserEntity update(Long userId, PutUserDto putUserDto) {
        if (userId != null){
            userEntityRepository.findById(userId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setUsername(putUserDto.username());
                                updated.setPassword(passwordEncoder.encode(putUserDto.password()));
                                UserEntity userEntity = userEntityRepository.save(updated);
                                return userEntityRepository.save(userEntity);
                            }
                    );
        }
        return userEntityRepository.findById(Objects.requireNonNull(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<UserEntity> findAll() {
        return userEntityRepository.findAll();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userEntityRepository.existsByUsername(username);
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
