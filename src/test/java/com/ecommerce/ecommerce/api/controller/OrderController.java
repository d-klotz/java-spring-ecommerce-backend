package com.ecommerce.ecommerce.api.controller;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.entities.Order;
import com.ecommerce.ecommerce.api.entities.OrderItem;
import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.ShippingMethod;
import com.ecommerce.ecommerce.api.services.CustomerService;
import com.ecommerce.ecommerce.api.services.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private CustomerService customerService;

    private static final String BASE_URL = "/api/order/";
    private static final Date DATE = new Date();
    private static final Long ID = 1L;
    private static final List<OrderItem> ORDER_ITEMS = new ArrayList<OrderItem>();
    private static final Customer CUSTOMER = new Customer();
    private static final PaymentMethod PAYMENT_METHOD = PaymentMethod.PAYPAL;
    private static final ShippingMethod SHIPPING_METHOD = ShippingMethod.FASTEST;
    private static final Date ORDER_DATE = new Date();


    @Test
    public void createOrder() throws Exception {
        Order order = getOrderData();
        BDDMockito.given(this.customerService.getCustomerById(Mockito.anyLong())).willReturn(new Customer());
        BDDMockito.given(this.orderService.createOrder(Mockito.any(Order.class))).willReturn(order);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
                .content(this.getRequestJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private String getRequestJson() throws JsonProcessingException {
        Order order = new Order();

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(order);
    }

    private Order getOrderData() {
        Order order = new Order();
        order.setCustomer(CUSTOMER);
        order.getCustomer().setId(1L);
        order.setPaymentMethod(PAYMENT_METHOD);
        order.setShippingMethod(SHIPPING_METHOD);
//        order.setOrderItem(ORDER_ITEMS);
        return order;
    }

}
