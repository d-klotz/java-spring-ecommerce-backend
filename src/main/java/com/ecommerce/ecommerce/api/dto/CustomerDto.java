package com.ecommerce.ecommerce.api.dto;

import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.Profile;

import java.util.Optional;

public class CustomerDto {

    private Optional<Long> id = Optional.empty();
    private String name;
    private String email;
    private PaymentMethod mainPaymentMethod;
    private Profile profile;

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
}
