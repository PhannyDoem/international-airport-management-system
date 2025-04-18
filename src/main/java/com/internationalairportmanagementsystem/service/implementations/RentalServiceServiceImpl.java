package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostRentalServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRentalServiceDto;
import com.internationalairportmanagementsystem.enetity.RentalService;
import com.internationalairportmanagementsystem.mappers.RentalServiceMapper;
import com.internationalairportmanagementsystem.repository.RentalServiceRepository;
import com.internationalairportmanagementsystem.service.interfaces.RentalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RentalServiceServiceImpl implements RentalServiceService {
    private final RentalServiceRepository  rentalServiceRepository;
    private final RentalServiceMapper rentalServiceMapper;

    @Autowired
    public RentalServiceServiceImpl(RentalServiceMapper rentalServiceMapper, RentalServiceRepository rentalServiceRepository) {
        this.rentalServiceMapper = rentalServiceMapper;
        this.rentalServiceRepository = rentalServiceRepository;
    }

    @Override
    public RentalService create(PostRentalServiceDto postRentalService) {
        RentalService rentalService = rentalServiceMapper.postToRentalService(postRentalService);
        return rentalServiceRepository.save(rentalService);
    }

    @Override
    public RentalService update(Long rentalId, PutRentalServiceDto putRentalServiceDto) {
        if (rentalId != null){
            rentalServiceRepository.findById(rentalId)
                    .stream()
                    .findFirst()
                    .map(
                            updated -> {
                                updated.setType(putRentalServiceDto.type());
                                updated.setRate(putRentalServiceDto.rate());
                                updated.setDescription(putRentalServiceDto.description());
                                RentalService rentalService = rentalServiceRepository.save(updated);
                                return rentalServiceRepository.save(rentalService);
                            }
                    );
        }
        return rentalServiceRepository.findById(Objects.requireNonNull(rentalId))
                .orElseThrow(()-> new RuntimeException("Updated Rental not found"));
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

}
