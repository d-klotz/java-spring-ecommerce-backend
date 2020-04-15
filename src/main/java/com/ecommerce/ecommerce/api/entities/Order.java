package com.ecommerce.ecommerce.api.entities;

import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.ShippingMethod;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    private int Id;
    private List<OrderItem> orderItem;
    private int quantity;
    private Customer customer;
    private PaymentMethod paymentMethod;
    private ShippingMethod shippingMethod;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(List<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }

    @Enumerated(EnumType.STRING)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Enumerated(EnumType.STRING)
    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }
}
