package com.internationalairportmanagementsystem.service.interfaces;

import com.internationalairportmanagementsystem.dtos.posts.PostAbilityDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;

import java.util.List;
import java.util.Optional;

public interface AbilityService {

    Ability create(PostAbilityDto postAbilityDto);


    Ability update(Long abilityId, PutAbilityDto putAbilityDto);

    Optional<Ability> findById(Long abilityId);

    List<Ability> findAll();

    String deleteById(Long abilityId);

    String deleteAll();
}
