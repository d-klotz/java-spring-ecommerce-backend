package com.ecommerce.ecommerce.api.dto;

import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.Profile;

import java.util.Optional;

public class CustomerDto {

    private Optional<Long> id = Optional.empty();
    private String name;
    private String email;
    private String password;
    private PaymentMethod mainPaymentMethod;
    private Profile profile;
    private String address;
    private String complement;
    private String number;

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentMethod getMainPaymentMethod() {
        return mainPaymentMethod;
    }

    public void setMainPaymentMethod(PaymentMethod mainPaymentMethod) {
        this.mainPaymentMethod = mainPaymentMethod;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
