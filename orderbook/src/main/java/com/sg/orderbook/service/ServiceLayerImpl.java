/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.entities.Transaction;
import com.sg.orderbook.repositories.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Minul
 */
@Service
public class ServiceLayerImpl implements ServiceLayer{

    @Autowired
    OrderRepository orders;
    
    @Autowired
    TransactionRepository transactions;
    
    @Override
    public List<Order> getAllOrders() {
        return orders.findAll();
    }

    @Override
    public List<Order> getAllActiveOrders() {
        return orders.findAllBuyOrders();
    }

    @Override
    public List<Transaction> getAllTransactions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Transaction> getAllTransactionsForSymbol(String symbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void makeTransaction(Order buyOrder, Order sellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean matchOrders(Order buyOrder, Order sellOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
