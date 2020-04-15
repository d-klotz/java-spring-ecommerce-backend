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
    private Date creationDate;
    private Date lastUpdateDate;
    private Profile profile;
    private PaymentMethod mainPaymentMethod;
    private List<Order> orderList;

    @Enumerated(EnumType.STRING)
    @Column(name = "main_payment_method")
    public PaymentMethod getMainPaymentMethod() {
        return mainPaymentMethod;
    }

    public void setMainPaymentMethod(PaymentMethod mainPaymentMethod) {
        this.mainPaymentMethod = mainPaymentMethod;
    }

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
    @Column(name = "orders")
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}
