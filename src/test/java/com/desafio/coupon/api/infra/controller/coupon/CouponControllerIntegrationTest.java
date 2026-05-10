package com.desafio.coupon.api.infra.controller.coupon;

import com.desafio.coupon.api.application.coupon.dto.CreateCouponRequestDto;
import com.desafio.coupon.api.builder.CouponControllerIntegrationBuilder;
import com.desafio.coupon.api.commons.test.TestTags;
import com.desafio.coupon.api.infra.persistence.coupon.repository.SpringDataCouponJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag(TestTags.CONTROLLER)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tst")
public class CouponControllerIntegrationTest extends CouponControllerIntegrationScenarios {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringDataCouponJpaRepository couponRepository;

    @BeforeEach
    void setUp() {
        couponRepository.deleteAll();
    }

    @Test()
    @Order(1)
    void testShouldGetAllCouponsEmpty() throws Exception {
        mockMvc.perform(get(API_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testShouldCreateCouponSuccessfully() throws Exception {
        final CouponControllerIntegrationBuilder builder = shouldCreateValidCoupon();
        final CreateCouponRequestDto request = builder.getValidRequest();

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.description").value(builder.getValidDescription()))
                .andExpect(jsonPath("$.discountValue").value(builder.getValidDiscountValue().doubleValue()))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void testShouldFailCreateByInvalidCode() throws Exception {
        final CouponControllerIntegrationBuilder builder = shouldFailCreateByInvalidCode();

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(builder.getInvalidRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(builder.getExpectedErrorMessage()))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.path").value(API_URL));
    }

    @Test
    void testShouldFailCreateByInvalidDiscount() throws Exception {
        final CouponControllerIntegrationBuilder builder = shouldFailCreateByInvalidDiscount();

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(builder.getInvalidRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(builder.getExpectedErrorMessage()));
    }

    @Test
    void testShouldFailCreateByInvalidExpirationDate() throws Exception {
        final CouponControllerIntegrationBuilder builder = shouldFailCreateByInvalidExpirationDate();

        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(builder.getInvalidRequest())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(builder.getExpectedErrorMessage()));
    }

    @Test
    void testShouldFailDeleteNonExistent() throws Exception {
        mockMvc.perform(delete(API_URL+"/{code}", "00XXXX")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("O cupom com código 00XXXX não foi encontrado."))
                .andExpect(jsonPath("$.status").value(400));
    }
}




