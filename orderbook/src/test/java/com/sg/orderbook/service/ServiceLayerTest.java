/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.repositories.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author R Lara
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ServiceLayerTest {
    
    @TestConfiguration
    static class ServiceLayerTestContextConfiguration {
        @Bean
        public ServiceLayer serviceLayer() {
            return new ServiceLayerImpl();
        }
    }
    

    @Autowired
    private TransactionRepository transactions;
    
    @Autowired
    private OrderRepository orders;

    @Autowired
    private ServiceLayerImpl service;
    
    @Test
    public void testGetAllOrders() {
        Order order = new Order();
        
        order.setActive(true);
        order.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        order.setSide(true);
        order.setSize(10);
        order.setSymbol("APPL");
        order.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));
        
        order = orders.save(order);
        
    }

    /*
    @Test
    public void testGetAllActiveOrders() {
    }

    @Test
    public void testGetAllTransactions() {
    }

    @Test
    public void testGetAllTransactionsForSymbol() {
    }

    @Test
    public void testDeleteOrder() {
    }

    @Test
    public void testMakeTransaction() {
    }

    @Test
    public void testMatchOrders() {
    }*/
}
