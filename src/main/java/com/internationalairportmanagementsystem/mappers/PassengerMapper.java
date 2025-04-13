package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostPassengerDto;
import com.internationalairportmanagementsystem.dtos.puts.PutPassengerDto;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.enetity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PassengerMapper {

    private PasswordEncoder passwordEncoder;

    @Autowired
    public PassengerMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    public Passenger postToPassenger(PostPassengerDto postPassengerDto) {
        Passenger passenger = new Passenger(
                postPassengerDto.name(),
                postPassengerDto.passportNumber(),
                postPassengerDto.nationality(),
                postPassengerDto.nationality(),
                postPassengerDto.userEntity()
        );
        passenger.setPassengerId(0L);
        UserEntity user = new UserEntity(postPassengerDto.userEntity().getUsername(), passwordEncoder.encode(postPassengerDto.userEntity().getPassword()));
        passenger.setUserEntity(user);
        return passenger;
    }

    public Passenger putToPassenger(PutPassengerDto putPassengerDto) {
        Passenger passenger = new Passenger(
                putPassengerDto.name(),
                putPassengerDto.passportNumber(),
                putPassengerDto.nationality(),
                putPassengerDto.nationality(),
                putPassengerDto.userEntity()
        );
        passenger.setPassengerId(0L);
        UserEntity user = new UserEntity(putPassengerDto.userEntity().getUsername(), passwordEncoder.encode(putPassengerDto.userEntity().getPassword()));
        passenger.setUserEntity(user);
        return passenger;
    }

}
