package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAbilityDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;

import java.util.List;

public interface AbilityService {

    Ability create(PostAbilityDto postAbilityDto);


    Ability update(PutAbilityDto putAbilityDto);

    Ability findById(Long abilityId);

    List<Ability> findAll();

    String deleteById(Long abilityId);

    String deleteAll();
}
