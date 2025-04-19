package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Cargo;
import com.internationalairportmanagementsystem.mappers.CargoMapper;
import com.internationalairportmanagementsystem.repository.CargoRepository;
import com.internationalairportmanagementsystem.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {
    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;
    @Autowired
    public  CargoServiceImpl(CargoRepository cargoRepository, CargoMapper cargoMapper) {
        this.cargoRepository = cargoRepository;
        this.cargoMapper = cargoMapper;
    }
    @Override
    public Cargo create(PostCargoDto postCargoDto) {
        Cargo cargo = cargoMapper.postToCargo(postCargoDto);
        return cargoRepository.save(cargo);
    }

    @Override
    public Cargo update(Long cargoId, PutCargoDto putCargoDto) {
        if (cargoId != null){
            cargoRepository.findById(cargoId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setWeight(putCargoDto.weight());
                                updated.setFlight(putCargoDto.flight());
                                updated.setDimension(putCargoDto.dimension());
                                Cargo cargo = cargoRepository.save(updated);
                                return cargoRepository.save(cargo);
                            }
                    );
        }
        return cargoRepository.findById(Objects.requireNonNull(cargoId))
                .orElseThrow(() -> new RuntimeException("Update Cargo with Id not found"));
    }

    @Override
    public Cargo findById(Long cargoId) {
        return cargoRepository.findById(cargoId)
                .orElseThrow(() -> new RuntimeException("Find Cargo with Id not found"));
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public String deleteById(Long cargoId) {
        cargoRepository.deleteById(cargoId);
        return "Deleted Cargo with Id " + cargoId;
    }
}
