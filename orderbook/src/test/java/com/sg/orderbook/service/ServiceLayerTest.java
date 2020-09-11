/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.entities.Transaction;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author R Lara
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(true)
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

        Order gotOrder = orders.findById(order.getId()).orElse(null);

        assertEquals(order, gotOrder);
    }

    @Test
    public void testGetAllActiveOrders() {
        Order firstOrder = new Order();

        firstOrder.setActive(true);
        firstOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        firstOrder.setSide(true);
        firstOrder.setSize(10);
        firstOrder.setSymbol("APPL");
        firstOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        firstOrder = orders.save(firstOrder);

        Order secondOrder = new Order();

        secondOrder.setActive(false);
        secondOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        secondOrder.setSide(true);
        secondOrder.setSize(0);
        secondOrder.setSymbol("APPL");
        secondOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));
        
        secondOrder = orders.save(secondOrder);

        Order gotFirstOrder = orders.findById(firstOrder.getId()).orElse(null);
        Order gotSecondOrder = orders.findById(secondOrder.getId()).orElse(null);
        List<Order> gotActiveOrders = orders.findAllActiveOrders();

        assertEquals(firstOrder, gotFirstOrder);
        assertEquals(secondOrder, gotSecondOrder);
        assertEquals(1, gotActiveOrders.size());
    }

    @Test
    public void testGetAllBuyOrders() {
        // Create Buy Order
        Order buyOrder = new Order();

        buyOrder.setActive(true);
        buyOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        buyOrder.setSide(true);
        buyOrder.setSize(10);
        buyOrder.setSymbol("APPL");
        buyOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        buyOrder = orders.save(buyOrder);

        // Create Sell Order
        Order sellOrder = new Order();

        sellOrder.setActive(false);
        sellOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        sellOrder.setSide(true);
        sellOrder.setSize(0);
        sellOrder.setSymbol("APPL");
        sellOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        sellOrder = orders.save(sellOrder);

        // Retrieve orders, list of buy orders
        Order gotBuyOrder = orders.findById(buyOrder.getId()).orElse(null);
        Order gotSellOrder = orders.findById(sellOrder.getId()).orElse(null);
        List<Order> gotBuyOrders = service.getAllBuyOrders();

        // Assert
        assertEquals(buyOrder, gotBuyOrder);
        assertEquals(sellOrder, gotSellOrder);
        assertEquals(1, gotBuyOrders.size());
        assertEquals(gotBuyOrders.get(0), gotBuyOrder);
        assertEquals(gotBuyOrders.get(0).isSide(), true);
    }

    @Test
    public void testGetAllSellOrders() {
        // Create Buy Order
        Order buyOrder = new Order();

        buyOrder.setActive(true);
        buyOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        buyOrder.setSide(true);
        buyOrder.setSize(10);
        buyOrder.setSymbol("APPL");
        buyOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        buyOrder = orders.save(buyOrder);

        // Create Sell Order
        Order sellOrder = new Order();

        sellOrder.setActive(false);
        sellOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        sellOrder.setSide(false);
        sellOrder.setSize(0);
        sellOrder.setSymbol("APPL");
        sellOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        sellOrder = orders.save(sellOrder);

        // Retrieve orders, list of sell orders
        Order gotBuyOrder = orders.findById(buyOrder.getId()).orElse(null);
        Order gotSellOrder = orders.findById(sellOrder.getId()).orElse(null);
        List<Order> gotSellOrders = service.getAllSellOrders();

        // Assert
        assertEquals(buyOrder, gotBuyOrder);
        assertEquals(sellOrder, gotSellOrder);
        assertEquals(1, gotSellOrders.size());
        assertEquals(gotSellOrders.get(1), gotBuyOrder);
        assertEquals(gotSellOrders.get(1).isSide(), true);
    }

    @Test
    public void testGetAllTransactions() {
        // Create Buy Order
        Order buyOrder = new Order();

        buyOrder.setActive(true);
        buyOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        buyOrder.setSide(true);
        buyOrder.setSize(10);
        buyOrder.setSymbol("APPL");
        buyOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        buyOrder = orders.save(buyOrder);

        // Create Sell Order
        Order sellOrder = new Order();

        sellOrder.setActive(false);
        sellOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        sellOrder.setSide(true);
        sellOrder.setSize(0);
        sellOrder.setSymbol("APPL");
        sellOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        sellOrder = orders.save(sellOrder);

        // Retrieve orders, list of sell orders
        Order gotBuyOrder = orders.findById(buyOrder.getId()).orElse(null);
        Order gotSellOrder = orders.findById(sellOrder.getId()).orElse(null);

        boolean buySizeBigger = gotBuyOrder.getSize() > gotSellOrder.getSize();

        Transaction transaction = new Transaction();
        transaction.setBuyOrder(buyOrder);
        transaction.setSellOrder(sellOrder);
        transaction.setFinalPrice(gotBuyOrder.getOfferPrice());
        transaction.setFinalSymbol(gotBuyOrder.getSymbol());
        transaction.setAmount(buySizeBigger ? gotBuyOrder.getSize() - gotSellOrder.getSize()
                : gotSellOrder.getSize() - gotBuyOrder.getSize());
        transaction.setFinalTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        gotBuyOrder.setSize(buySizeBigger ? gotBuyOrder.getSize() - transaction.getSellOrder().getSize() : 0);
        gotSellOrder.setSize(buySizeBigger ? 0 : gotSellOrder.getSize() - transaction.getBuyOrder().getSize());

        transaction = transactions.save(transaction);
        gotBuyOrder = orders.save(gotBuyOrder);
        gotSellOrder = orders.save(gotSellOrder);

        List<Transaction> listTransactions = service.getAllTransactions();
        assertNotNull(listTransactions);
        assertEquals(1, listTransactions.size());
        assertEquals(gotBuyOrder, transaction.getBuyOrder());
        assertEquals(gotSellOrder, transaction.getSellOrder());
    }

    @Test
    public void testGetAllTransactionsForSymbol() {
        // Create AAPL Buy Order
        Order buyOrder = new Order();

        buyOrder.setActive(true);
        buyOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        buyOrder.setSide(true);
        buyOrder.setSize(10);
        buyOrder.setSymbol("AAPL");
        buyOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        buyOrder = orders.save(buyOrder);

        // Create AAPL Sell Order
        Order sellOrder = new Order();

        sellOrder.setActive(false);
        sellOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        sellOrder.setSide(true);
        sellOrder.setSize(0);
        sellOrder.setSymbol("AAPL");
        sellOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        sellOrder = orders.save(sellOrder);

        // Retrieve orders, list of sell orders
        Order gotBuyOrder = orders.findById(buyOrder.getId()).orElse(null);
        Order gotSellOrder = orders.findById(sellOrder.getId()).orElse(null);

        boolean buySizeBigger = gotBuyOrder.getSize() > gotSellOrder.getSize();

        Transaction transaction = new Transaction();
        transaction.setBuyOrder(buyOrder);
        transaction.setSellOrder(sellOrder);
        transaction.setFinalPrice(gotBuyOrder.getOfferPrice());
        transaction.setFinalSymbol(gotBuyOrder.getSymbol());
        transaction.setAmount(buySizeBigger ? gotBuyOrder.getSize() - gotSellOrder.getSize()
                : gotSellOrder.getSize() - gotBuyOrder.getSize());
        transaction.setFinalTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        gotBuyOrder.setSize(buySizeBigger ? gotBuyOrder.getSize() - transaction.getSellOrder().getSize() : 0);
        gotSellOrder.setSize(buySizeBigger ? 0 : gotSellOrder.getSize() - transaction.getBuyOrder().getSize());

        transaction = transactions.save(transaction);
        gotBuyOrder = orders.save(gotBuyOrder);
        gotSellOrder = orders.save(gotSellOrder);

        List<Transaction> aaplTransactions = service.getAllTransactionsForSymbol(gotBuyOrder.getSymbol());

        assertEquals(1, aaplTransactions.size());
        assertEquals(gotBuyOrder, transaction.getBuyOrder());
        assertEquals(gotSellOrder, transaction.getSellOrder());
    }

    @Test
    public void testDeleteOrder() {
        Order order = new Order();

        order.setActive(true);
        order.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        order.setSide(true);
        order.setSize(10);
        order.setSymbol("APPL");
        order.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        order = orders.save(order);

        Order gotOrder = orders.findById(order.getId()).orElse(null);
        
        assertNotNull(gotOrder);

        orders.deleteById(gotOrder.getId());

        assertEquals(0, orders.findAll().size());
    }

    @Test
    public void testMakeTransaction() {
        Order firstOrder = new Order();

        firstOrder.setActive(true);
        firstOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        firstOrder.setSide(true);
        firstOrder.setSize(10);
        firstOrder.setSymbol("APPL");
        firstOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        firstOrder = orders.save(firstOrder);

        Order secondOrder = new Order();

        secondOrder.setActive(true);
        secondOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        secondOrder.setSide(!firstOrder.isSide());
        secondOrder.setSize(10);
        secondOrder.setSymbol("APPL");
        secondOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        secondOrder = orders.save(secondOrder);

        Transaction transaction = service.makeTransaction(firstOrder, secondOrder);

        assertEquals(firstOrder, transaction.getBuyOrder());
        assertEquals(secondOrder, transaction.getSellOrder());
        assertEquals(0, transaction.getBuyOrder().getSize());
        assertEquals(0, transaction.getSellOrder().getSize());
    }

    @Test
    public void testMatchOrders() {
        Order firstOrder = new Order();

        firstOrder.setActive(true);
        firstOrder.setOfferPrice(new BigDecimal("100").setScale(2, RoundingMode.HALF_UP));
        firstOrder.setSide(true);
        firstOrder.setSize(10);
        firstOrder.setSymbol("APPL");
        firstOrder.setTime(LocalDateTime.parse("2020-01-01T12:00:00"));

        firstOrder = orders.save(firstOrder);
        
        Transaction transaction = service.matchOrders(firstOrder.getId());
        Order secondOrder = firstOrder.isSide() 
                ? transaction.getSellOrder() 
                : transaction.getBuyOrder();
        
        assertNotNull(secondOrder);
        assertEquals(1, transactions.findAll().size());
        assertEquals(firstOrder.getOfferPrice(), secondOrder.getOfferPrice());
        assertEquals(firstOrder.getSymbol(), secondOrder.getSymbol());
        assertNotEquals(firstOrder.isSide(), secondOrder.isSide());
    }

}
