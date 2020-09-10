/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import com.sg.orderbook.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

/**
 *
 * @author R Lara
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ServiceLayerTest {

    @Autowired
    private OrderRepository orders;
    
    @Autowired
    private TransactionRepository transactions;

    /**
     * Test of getAllOrders method, of class ServiceLayer.
     */
    @Test
    public void testGetAllOrders() {
    }

    /**
     * Test of getAllActiveOrders method, of class ServiceLayer.
     */
    @Test
    public void testGetAllActiveOrders() {
    }

    /**
     * Test of getAllTransactions method, of class ServiceLayer.
     */
    @Test
    public void testGetAllTransactions() {
    }

    /**
     * Test of getAllTransactionsForSymbol method, of class ServiceLayer.
     */
    @Test
    public void testGetAllTransactionsForSymbol() {
    }

    /**
     * Test of deleteOrder method, of class ServiceLayer.
     */
    @Test
    public void testDeleteOrder() {
    }

    /**
     * Test of makeTransaction method, of class ServiceLayer.
     */
    @Test
    public void testMakeTransaction() {
    }

    /**
     * Test of matchOrders method, of class ServiceLayer.
     */
    @Test
    public void testMatchOrders() {
    }
    
}
