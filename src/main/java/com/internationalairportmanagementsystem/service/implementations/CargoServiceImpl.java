package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Baggage;
import com.internationalairportmanagementsystem.enetity.Cargo;
import com.internationalairportmanagementsystem.mappers.CargoMapper;
import com.internationalairportmanagementsystem.repository.CargoRepository;
import com.internationalairportmanagementsystem.service.interfaces.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoServiceImpl implements CargoService {
    private CargoRepository cargoRepository;
    private CargoMapper cargoMapper;
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
    public Cargo update(PutCargoDto putCargoDto) {
        Cargo cargo = cargoMapper.putToCargo(putCargoDto);
        return cargoRepository.save(cargo);
    }

    @Override
    public Cargo findById(Long cargoId) {
        Optional<Cargo> result = cargoRepository.findById(cargoId);
        Cargo cargo = null;
        if(result.isPresent()) {
            cargo = result.get();
        }else {
            throw new RuntimeException("Not Found Baggage with Id " + cargoId);
        }
        return cargo;
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
