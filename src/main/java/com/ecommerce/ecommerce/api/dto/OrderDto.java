package com.ecommerce.ecommerce.api.dto;

import com.ecommerce.ecommerce.api.enums.OrderStatus;
import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.ShippingMethod;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderDto {

    private Optional<Long> id = Optional.empty();
    private Long customerId;
    private List<OrderItemDto> orderItems;
    private PaymentMethod paymentMethod;
    private ShippingMethod shippingMethod;
    private OrderStatus status;
    private double totalAmount;
    private Optional<Date> creationDate = Optional.empty();

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Optional<Date> getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Optional<Date> creationDate) {
        this.creationDate = creationDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
