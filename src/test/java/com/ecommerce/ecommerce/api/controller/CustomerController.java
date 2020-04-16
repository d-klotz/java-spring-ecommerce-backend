package com.ecommerce.ecommerce.api.controller;

import com.ecommerce.ecommerce.api.entities.Customer;
import com.ecommerce.ecommerce.api.enums.PaymentMethod;
import com.ecommerce.ecommerce.api.enums.Profile;
import com.ecommerce.ecommerce.api.services.CustomerService;
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

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CustomerController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private static final String BASE_URL = "/api/customer/";
    private static final Date DATE = new Date();
    private static final Long ID = 1L;
    private static final String NAME = "test user";
    private static final PaymentMethod PAYMENT_METHOD = PaymentMethod.PAYPAL;
    private static final String EMAIL = "user@test.com";

    @Test
    public void createCustomer() throws Exception {
        Customer customer = getCustomerData();
        BDDMockito.given(this.customerService.createCustomer(Mockito.any(Customer.class))).willReturn(customer);

        mockMvc.perform(MockMvcRequestBuilders.post(BASE_URL)
            .content(this.getRequestJson())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
    }

    private String getRequestJson() throws JsonProcessingException {
        Customer customer = new Customer();
        customer.setId(null);
        customer.setEmail(EMAIL);
        customer.setMainPaymentMethod(PAYMENT_METHOD);
        customer.setName(NAME);
        customer.setProfile(Profile.ROLE_USER);
        customer.setCreationDate(DATE);
        customer.setLastUpdateDate(DATE);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(customer);
    }

    private Customer getCustomerData() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setEmail(EMAIL);
        customer.setMainPaymentMethod(PaymentMethod.PAYPAL);
        customer.setName(NAME);
        customer.setProfile(Profile.ROLE_USER);
        customer.setCreationDate(DATE);
        customer.setLastUpdateDate(DATE);
        return customer;
    }
}
