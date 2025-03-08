package com.internationalairportmanagementsystem.enetity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    @JsonIgnoreProperties({"role", "passenger", "employee"})
    private List<UserEntity> users;

    @ManyToMany
    @JoinTable(
            name = "role_abilities",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "abilities_id")
    )
    private List<Ability> abilities;

    @PreRemove
    public void preRemove(){
        for(UserEntity userEntity : users){
            userEntity.setRole(null);
        }
    }

    public Role(){}

    public Role(String roleName){
        this.roleName = roleName;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "abilities=" + abilities +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }

    public void addAbility(Ability ability){
        if(this.abilities == null){
            this.abilities = new ArrayList<>();
        }
    }

}
