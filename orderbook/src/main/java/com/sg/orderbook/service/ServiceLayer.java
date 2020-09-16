/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import org.springframework.stereotype.Component;
import com.sg.orderbook.entities.*;
import java.util.List;

/**
 *
 * @author Minul
 */
@Component
public interface ServiceLayer {
//    public List<Order> getAllOrders();
//    
//    public List<Order> getAllActiveOrders();

    public List<Order> getAllBuyOrdersForSymbol(String symbol);
    
    public List<Order> getAllSellOrdersForSymbol(String symbol);

    public List<Transaction> getAllTransactions();

    public List<Transaction> getAllTransactionsForSymbol(String symbol);
    
    public void deleteUnmatchedOrder(int orderId);
    
    public Transaction makeTransaction(Order buyOrder, Order sellOrder);
    
    public Transaction matchOrders(int givenOrderId);
    
    public void findPotentialTransactions(String symbol);
    
    public List getSymbols();
    
    public void createOrderbook(String symbol);
    
    public void addOrder(Order newOrder);
}
