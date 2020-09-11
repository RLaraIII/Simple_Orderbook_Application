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
    public List<Order> getAllBuyOrders();
    
    public List<Order> getAllSellOrders();
    
    public List<Transaction> getAllTransactions();
    
    public List<Transaction> getAllTransactionsForSymbol(String symbol);
    
    public void deleteUnmatchedOrder(Order order);
    
    public void makeTransaction(Order buyOrder, Order sellOrder);
    
    public boolean matchOrders(Order buyOrder, Order sellOrder);
}
