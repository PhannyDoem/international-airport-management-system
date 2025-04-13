package com.internationalairportmanagementsystem.enetity;


import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "abilities")
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abilities_id")
    private Long abilityId;
    private String entity;
    @Column(name = "verb")
    private String verb;

    @Column(name = "field")
    private String field;

    @ManyToMany(mappedBy = "abilities")
    @JsonIncludeProperties({"abilities", "users"})
     private List<Role> roles;

    @PreRemove
   public void preRemove(){
       for(Role role : roles){
           role.getAbilities().remove(this);
       }
   }

   public Ability(){}

    public Ability(String entity, String verb, String field) {
       this.entity = entity;
       this.verb = verb;
       this.field = field;
    }

    public Long getAbilityId() {
        return abilityId;
    }

    public void setAbilityId(Long abilityId) {
        this.abilityId = abilityId;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "abilityId=" + abilityId +
                ", entity='" + entity + '\'' +
                ", verb='" + verb + '\'' +
                ", field='" + field + '\'' +
                ", roles=" + roles +
                '}';
    }
}
