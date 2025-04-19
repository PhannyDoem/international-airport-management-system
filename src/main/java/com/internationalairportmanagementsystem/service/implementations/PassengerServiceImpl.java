package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostPassengerDto;
import com.internationalairportmanagementsystem.dtos.puts.PutPassengerDto;
import com.internationalairportmanagementsystem.enetity.Passenger;
import com.internationalairportmanagementsystem.mappers.PassengerMapper;
import com.internationalairportmanagementsystem.repository.PassengerRepository;
import com.internationalairportmanagementsystem.service.interfaces.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository,
                                PassengerMapper passengerMapper
    ) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public Passenger create(PostPassengerDto postPassengerDto) {
        Passenger passenger = passengerMapper.postToPassenger(postPassengerDto);
        return passengerRepository.save(passenger);
    }

    @Override
    public Passenger update(Long passengerId, PutPassengerDto putPassengerDto) {
        if (passengerId != null){
            passengerRepository.findById(passengerId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setName(putPassengerDto.name());
                                updated.setNationality(putPassengerDto.nationality());
                                updated.setContactDetails(putPassengerDto.contactDetails());
                                updated.setUserEntity(putPassengerDto.userEntity());
                                Passenger passenger = passengerRepository.save(updated);
                                return passengerRepository.save(passenger);
                            }
                    );
        }
        return passengerRepository.findById(Objects.requireNonNull(passengerId))
                .orElseThrow(()-> new RuntimeException("Updated Passenger not found"));
    }

    @Override
    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    @Override
    public Passenger findById(Long passengerId) {
        return passengerRepository.findById(passengerId).orElse(null);
    }

    @Override
    public Passenger findByUserEntityId(Long userEntityId) {
        Optional<Passenger> result = passengerRepository.findByUserEntityId(userEntityId);

        Passenger thePassenger = null;

        if (result.isPresent()) {
            thePassenger = result.get();
        }
        else {
            throw new RuntimeException("Did not find passenger by user id - " + userEntityId);
        }

        return thePassenger;
    }

    @Override
    public String deleteById(Long passengerId) {
        passengerRepository.deleteById(passengerId);
        return "Delete Passenger Successfully";
    }
}
