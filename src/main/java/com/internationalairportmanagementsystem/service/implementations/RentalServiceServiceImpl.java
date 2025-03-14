package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostRentalService;
import com.internationalairportmanagementsystem.dtos.puts.PutRentalServiceDto;
import com.internationalairportmanagementsystem.enetity.RentalService;
import com.internationalairportmanagementsystem.mappers.RentalServiceMapper;
import com.internationalairportmanagementsystem.repository.RentalServiceRepository;
import com.internationalairportmanagementsystem.service.interfaces.RentalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceServiceImpl implements RentalServiceService {
    private RentalServiceRepository  rentalServiceRepository;
    private RentalServiceMapper rentalServiceMapper;

    @Autowired
    public RentalServiceServiceImpl(RentalServiceMapper rentalServiceMapper, RentalServiceRepository rentalServiceRepository) {
        this.rentalServiceMapper = rentalServiceMapper;
        this.rentalServiceRepository = rentalServiceRepository;
    }

    @Override
    public RentalService create(PostRentalService postRentalService) {
        RentalService rentalService = rentalServiceMapper.postToRentalService(postRentalService);
        return rentalServiceRepository.save(rentalService);
    }

    @Override
    public RentalService update(PutRentalServiceDto putRentalServiceDto) {
        RentalService rentalService =  rentalServiceMapper.putToRentalService(putRentalServiceDto);
         return rentalServiceRepository.save(rentalService);
    }

    @Override
    public List<RentalService> findAll() {
        return rentalServiceRepository.findAll();
    }

    @Override
    public RentalService findById(Long rentalServiceId) {
        return rentalServiceRepository.findById(rentalServiceId).orElse(null);
    }

    @Override
    public String deleteById(Long rentalServiceId) {
        rentalServiceRepository.deleteById(rentalServiceId);
        return "Delete RentalService Successfully";
    }

    @Override
    public String deleteAll() {
        rentalServiceRepository.deleteAll();
        return "Delete All RentalService Successfully";
    }
}
