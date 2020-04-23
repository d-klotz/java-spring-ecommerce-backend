package com.ecommerce.ecommerce.api.entities;

import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.Profile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Date creationDate;
    private Date lastUpdateDate;
    private Profile profile;
    private PaymentMethod mainPaymentMethod;
    private List<Order> orders;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Enumerated(EnumType.STRING)
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "creation_date")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "last_update_date")
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdateDate = new Date();
    }

    @PrePersist
    public void prePersist(){
        final Date date = new Date();
        lastUpdateDate = date;
        creationDate = date;
    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orderList) {
        this.orders = orderList;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "main_payment_method")
    public PaymentMethod getMainPaymentMethod() {
        return mainPaymentMethod;
    }

    public void setMainPaymentMethod(PaymentMethod mainPaymentMethod) {
        this.mainPaymentMethod = mainPaymentMethod;
    }

}
