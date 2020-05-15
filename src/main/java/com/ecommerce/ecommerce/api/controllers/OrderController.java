package com.ecommerce.ecommerce.api.controllers;

import com.ecommerce.ecommerce.api.dto.OrderDto;
import com.ecommerce.ecommerce.api.dto.OrderItemDto;
import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.entities.Order;
import com.ecommerce.ecommerce.api.entities.OrderItem;
import com.ecommerce.ecommerce.api.entities.Product;
import com.ecommerce.ecommerce.api.response.Response;
import com.ecommerce.ecommerce.api.security.utils.JwtTokenUtil;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.OrderItemService;
import com.ecommerce.ecommerce.api.services.OrderService;
import com.ecommerce.ecommerce.api.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*")
public class OrderController {

    private static Logger log = LoggerFactory.getLogger(CustomerService.class);
    private static final String TOKEN_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
      log.info("Creating order {}", orderDto);
      Order order = convertOrderDtoToOrder(orderDto);
      order.setTotalAmount(calculateTotalAmount(orderDto));
      Order createdOrder = this.orderService.createOrder(order);
      OrderDto createdOrderDto = convertoOrderToOrderDto(createdOrder);

      return ResponseEntity.ok(createdOrderDto);
    }

    @GetMapping()
    public ResponseEntity<Response<List<OrderDto>>> getOrdersByCustomerId(HttpServletRequest request) {

        Response<List<OrderDto>> response = new Response<>();
        Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));
        if (token.isPresent() && token.get().startsWith(BEARER_PREFIX)) {
            token = Optional.of(token.get().substring(7));
        }

        if (!token.isPresent()) {
            response.getErrors().add("Token not found.");
        } else if (!jwtTokenUtil.isTokenValid(token.get())) {
            response.getErrors().add("Token expired or invalid.");
        }

        if (!response.getErrors().isEmpty()) {
            return ResponseEntity.badRequest().body(response);
        }

        Optional<Customer> customer = this.customerService
                .getCustomerByEmail(jwtTokenUtil.getUsernameFromToken(token.get()));

        List<Order> orders = this.orderService.getOrdersByCustomerId(customer.get().getId());
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> orderDtos.add(convertoOrderToOrderDto(order)));
        response.setData(orderDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<OrderDto> getOrdersById(@PathVariable("id")  Long id) {
        Order order = this.orderService.getOrderById(id);
        OrderDto orderDto = convertoOrderToOrderDto(order);
        return ResponseEntity.ok(orderDto);
    }

    private double calculateTotalAmount(OrderDto orderDto) {
        AtomicReference<Double> totalAmount = new AtomicReference<>((double) 0);
        orderDto.getOrderItems().forEach(item -> {
            Product product = productService.getProductById(item.getProductId());
            totalAmount.set(totalAmount.get() + (product.getPrice() * item.getQuantity()));
        });
        return totalAmount.get();
    }

    private OrderDto convertoOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(Optional.of(order.getId()));
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setOrderItems(convertoOrderItemToOrderItemDto(order.getOrderItem()));
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setShippingMethod(order.getShippingMethod());
        orderDto.setStatus(order.getStatus());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setCreationDate(Optional.of(order.getCreationDate()));
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
        order.setStatus((orderDto.getStatus()));
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
