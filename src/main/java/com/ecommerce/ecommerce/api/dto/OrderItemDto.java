package com.ecommerce.ecommerce.api.dto;

import java.util.Optional;

public class OrderItemDto {

    private Optional<Long> id = Optional.empty();
    private Long productId;
    private Integer quantity;

    public Optional<Long> getId() {
        return id;
    }

    public void setId(Optional<Long> id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
