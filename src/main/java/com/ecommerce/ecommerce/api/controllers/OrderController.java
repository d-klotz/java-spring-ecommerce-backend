package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.dto.OrderDto;
import com.ecommerce.ecommerce.api.dto.OrderItemDto;
import com.ecommerce.ecommerce.api.entities.Order;
import com.ecommerce.ecommerce.api.entities.OrderItem;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.OrderItemService;
import com.ecommerce.ecommerce.api.services.OrderService;
import com.ecommerce.ecommerce.api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/order")
@CrossOrigin(origins = "*")
public class OrderController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
      log.info("Creating order {}", orderDto);
      Order order = convertOrderDtoToOrder(orderDto);

      Order createdOrder = this.orderService.createOrder(order);
      OrderDto createdOrderDto = convertoOrderToOrderDto(createdOrder);

      return ResponseEntity.ok(createdOrderDto);
    }

    private OrderDto convertoOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(Optional.of(order.getId()));
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setOrderItems(convertoOrderItemToOrderItemDto(order.getOrderItem()));
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setShippingMethod(order.getShippingMethod());
        return orderDto;
    }

    private List<OrderItemDto> convertoOrderItemToOrderItemDto(List<OrderItem> orderItems) {
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            OrderItemDto orderItemDto = new OrderItemDto();
            orderItemDto.setId(Optional.of(orderItem.getId()));
            orderItemDto.setProductId(orderItem.getProduct().getId());
            orderItemDto.setQuantity(orderItem.getQuantity());
            orderItemDtos.add(orderItemDto);
        });
        return orderItemDtos;
    }

    private Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomer(customerService.getCustomerById(orderDto.getCustomerId()));
        order.setShippingMethod(orderDto.getShippingMethod());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setOrderItem(convertOrderItemsDtoToOrdemItems(orderDto.getOrderItems(), order));
        return order;
    }

    private List<OrderItem> convertOrderItemsDtoToOrdemItems(List<OrderItemDto> orderItemsDto, Order order) {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemsDto.forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productService.getProductById(orderItemDto.getProductId()));
            orderItem.setQuantity(orderItemDto.getQuantity());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }
}
