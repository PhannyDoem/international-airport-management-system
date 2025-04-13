package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostRentalServiceDto;
import com.internationalairportmanagementsystem.dtos.puts.PutRentalServiceDto;
import com.internationalairportmanagementsystem.enetity.RentalService;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceMapper {
    public RentalService postToRentalService(PostRentalServiceDto postRentalService) {
        RentalService rentalService = new RentalService(
                postRentalService.type(),
                postRentalService.description(),
                postRentalService.rate()
        );
        rentalService.setRentalServiceId(0L);
        return rentalService;
    }

    public RentalService putToRentalService(PutRentalServiceDto putRentalServiceDto) {
        RentalService rentalService = new RentalService(
                putRentalServiceDto.type(),
                putRentalServiceDto.description(),
                putRentalServiceDto.rate()
        );
        rentalService.setRentalServiceId(0L);
        return rentalService;
    }
}
