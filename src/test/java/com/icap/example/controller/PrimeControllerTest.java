package com.icap.example.controller;

import com.icap.example.calculator.BasicPrimeCalculator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Created by rvarlikli on 4/21/16.
 */
public class PrimeControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        PrimeController primeController=new PrimeController();
        primeController.basicPrimeCalculator=new BasicPrimeCalculator();
        this.mockMvc = standaloneSetup(primeController).build();
    }

    @Test
    public void testBasicCalculationValidation() throws Exception {
        this.mockMvc.perform(get("/prime/basic/0"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testBasicCalculationHappyPath() throws Exception {
        this.mockMvc.perform(get("/prime/basic/10"))
                .andExpect(status().isOk())
        .andExpect(content().string("[2,3,5,7]"));
    }

}
