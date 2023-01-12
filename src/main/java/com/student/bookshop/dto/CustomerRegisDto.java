package com.student.bookshop.dto;

import com.student.bookshop.model.Region;
import com.student.bookshop.model.Role;

import java.util.List;

public class CustomerRegisDto {

    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;

    private Region region;
    private String district;

    private String neighborhood;

    private String address;

    private List<Role> roles;
    public CustomerRegisDto(String fullName, String phoneNumber, String email, String password,
                            Region region, String district, String neighborhood,
                            String address, List<Role> roles) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.region = region;
        this.district=district;
        this.neighborhood=neighborhood;
        this.address=address;
        this.roles=roles;
    }

    public CustomerRegisDto() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
