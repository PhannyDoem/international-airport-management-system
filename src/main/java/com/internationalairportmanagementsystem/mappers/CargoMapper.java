package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Cargo;
import com.internationalairportmanagementsystem.enetity.Flight;
import org.springframework.stereotype.Service;

@Service
public class CargoMapper {
    public Cargo postToCargo(PostCargoDto postCargoDto) {
        Cargo cargo = new Cargo(
                postCargoDto.weight(),
                postCargoDto.dimension(),
                postCargoDto.flight()
        );
        cargo.setCargoId(0L);
        return cargo;
    }

    public Cargo putToCargo(PutCargoDto putCargoDto) {
        Cargo cargo = new Cargo(
                putCargoDto.weight(),
                putCargoDto.dimension(),
                putCargoDto.flight()
        );
        cargo.setCargoId(0L);
        return cargo;
    }
}
