package com.internationalairportmanagementsystem.mappers;

import com.internationalairportmanagementsystem.dtos.posts.PostAbilityDto;
import com.internationalairportmanagementsystem.dtos.puts.PutAbilityDto;
import com.internationalairportmanagementsystem.enetity.Ability;
import org.springframework.stereotype.Service;

@Service
public class AbilityMapper {
    public Ability postToAbility(PostAbilityDto  postAbilityDto){
        Ability ability = new Ability(
                postAbilityDto.entity(),
                postAbilityDto.verb(),
                postAbilityDto.field()
        );
        ability.setAbilityId(0L);
        return ability;
    }

    public Ability putToAbility(PutAbilityDto putAbilityDto){
        Ability ability = new Ability(
                putAbilityDto.entity(),
                putAbilityDto.verb(),
                putAbilityDto.field()
        );
        ability.setAbilityId(0L);
        return ability;
    }
}
