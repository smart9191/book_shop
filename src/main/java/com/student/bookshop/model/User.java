package com.student.bookshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fullName;
        private String phoneNUmber;
        @NotNull
        @Size(min = 4,max = 50)
        @Column(length = 50,nullable = false,unique = true)
        private String email;
        @NotNull
        @Size(min = 20,max = 80)
        @Column(length = 80,nullable = false,unique = true)
        private String password;

        @ManyToOne(fetch = FetchType.EAGER)
        private Region region;

        private String district;

        private String neighborhood;

        private String address;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
        @JoinTable(
                name = "users_roles",
                joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")}
        )
        private List<Role> roles;

    public User(User user) {
        this.fullName = user.getFullName();
        this.phoneNUmber = user.phoneNUmber;
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.region = user.getRegion();
        this.neighborhood=user.getNeighborhood();
        this.district=user.getDistrict();
        this.address=user.getAddress();
        this.roles = user.getRoles();
    }

    public User(){

    }


    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
