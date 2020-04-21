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
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderDto orderDto) {
      log.info("Creating order {}", orderDto);
      Order order = convertOrderDtoToOrder(orderDto);
      this.orderService.createOrder(order);


      return ResponseEntity.ok(order);
    }

    private Order convertOrderDtoToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomer(customerService.getCustomerById(orderDto.getCustomerId()));
        order.setShippingMethod(orderDto.getShippingMethod());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setOrderItem(convertOrderItemsDtoToOrdemItems(orderDto.getOrderItems()));
        return order;
    }

    private List<OrderItem> convertOrderItemsDtoToOrdemItems(List<OrderItemDto> orderItemsDto) {
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemsDto.forEach(orderItemDto -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productService.getProductById(orderItemDto.getProductId()));
            orderItem.setQuantity(orderItemDto.getQuantity());
//            orderItem.setId(this.orderItemService.createOrderItem(orderItem).getId());
            orderItemList.add(orderItem);
        });
        return orderItemList;
    }
}
