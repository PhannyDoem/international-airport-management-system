package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostCargoDto;
import com.internationalairportmanagementsystem.dtos.puts.PutCargoDto;
import com.internationalairportmanagementsystem.enetity.Cargo;

import java.util.List;

public interface CargoService {
    Cargo create(PostCargoDto  postCargoDto);

    Cargo update(PutCargoDto putCargoDto);

    Cargo findById(Long cargoId);

    List<Cargo> findAll();

    String deleteById(Long cargoId);

}
