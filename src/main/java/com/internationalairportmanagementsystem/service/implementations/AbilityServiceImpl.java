package com.internationalairportmanagementsystem.service.implementations;

import com.internationalairportmanagementsystem.dtos.posts.PostAbilityDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import com.internationalairportmanagementsystem.mappers.AbilityMapper;
import com.internationalairportmanagementsystem.repository.AbilityRepository;
import com.internationalairportmanagementsystem.service.interfaces.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AbilityServiceImpl implements AbilityService {
    private final AbilityRepository abilityRepository;
    private final AbilityMapper abilityMapper;
    @Autowired
    public AbilityServiceImpl(AbilityRepository abilityRepository, AbilityMapper abilityMapper) {
        this.abilityRepository = abilityRepository;
        this.abilityMapper = abilityMapper;
    }
    @Override
    public Ability create(PostAbilityDto postAbilityDto) {
        Ability ability = abilityMapper.postToAbility(postAbilityDto);
        return abilityRepository.save(ability);
    }

    @Override
    public Ability update(Long abilityId, PutAbilityDto putAbilityDto) {
        if (abilityId != null) {
            abilityRepository.findById(abilityId).stream().findFirst().map(
                    updateAbility -> {
                        updateAbility.setEntity(putAbilityDto.entity());
                        updateAbility.setField(putAbilityDto.field());
                        updateAbility.setVerb(putAbilityDto.verb());
                        Ability ability = abilityRepository.save(updateAbility);
                        return abilityRepository.save(ability);
                    }
            );
        }
        return abilityRepository.findById(Objects.requireNonNull(abilityId)).orElseThrow(()-> new RuntimeException("Ability not found"));
    }

    @Override
    public Optional<Ability> findById(Long abilityId) {
        if (abilityId != null) {
            return abilityRepository.findById(abilityId);
        }
        return Optional.empty();
    }

    @Override
    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    @Override
    public String deleteById(Long abilityId) {
        if (abilityId != null) {
            abilityRepository.deleteById(abilityId);
        }
        return "Successfully deleted";

    }

    @Override
    public String deleteAll() {
        abilityRepository.deleteAll(abilityRepository.findAll());
        return "ability deleted successfully";
    }
}
