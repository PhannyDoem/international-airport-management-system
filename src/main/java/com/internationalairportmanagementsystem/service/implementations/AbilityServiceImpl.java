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
import java.util.Optional;

@Service
public class AbilityServiceImpl implements AbilityService {
    private AbilityRepository abilityRepository;
    private AbilityMapper abilityMapper;
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
    public Ability update(PutAbilityDto putAbilityDto) {
        Ability ability = abilityMapper.putToAbility(putAbilityDto);
        return abilityRepository.save(ability);
    }

    @Override
    public Ability findById(Long abilityId) {
        Optional<Ability> result = abilityRepository.findById(abilityId);
        Ability ability = null;
        if(result.isPresent()) {
            ability = result.get();
        }else {
            throw new RuntimeException("Ability with ID  " + abilityId + " not found");
        }
        return ability;
    }

    @Override
    public List<Ability> findAll() {
        return abilityRepository.findAll();
    }

    @Override
    public String deleteById(Long abilityId) {
        abilityRepository.deleteById(abilityId);
        return "ability deleted successfully";
    }

    @Override
    public String deleteAll() {
        abilityRepository.deleteAll(abilityRepository.findAll());
        return "ability deleted successfully";
    }
}
