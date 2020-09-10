/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.service;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.entities.Transaction;
import com.sg.orderbook.repositories.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    
//    @Override
//    public List<Order> getAllOrders() {
//        return orders.findAll();
//    }
//
//    @Override
//    public List<Order> getAllActiveOrders() {
//        return orders.findAllBySide();
//    }
    
    // get list of transactions stored in transaction database table
    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = transactions.findAll();
        return transactionList;
    }

    // Gets all transaction from database table and filters them to only return transactions that have the given symbol
    @Override
    public List<Transaction> getAllTransactionsForSymbol(String symbol) {
        // pull all transactions from database
        List<Transaction> allTransactions = getAllTransactions();
        
        // Loop through all transaction and only add it to the returned list if the symbol field equals the given symbol
        List<Transaction> transactionsForSymbol = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if (transaction.getFinalSymbol().equalsIgnoreCase(symbol)) {
                transactionsForSymbol.add(transaction);
            }
        }
        return transactionsForSymbol;
    }
    
    // Delete from order table in database using the given order's ID
    @Override
    public void deleteOrder(Order order) {
        orders.deleteById(order.getId());
    }
    
    // Create new transaction and save it to transaction database table; pull info from buy order
    @Override
    public void makeTransaction(Order buyOrder, Order sellOrder) {
        // Create the time field for the new transaction w/ format that sql can handle
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String formattedDateTime = now.format(formatter);
        
        // Create and fill a new transaction
        Transaction newTransaction = new Transaction();
        newTransaction.setBuyOrder(buyOrder);
        newTransaction.setSellOrder(sellOrder);
        newTransaction.setAmount(buyOrder.getSize());
        newTransaction.setFinalSymbol(buyOrder.getSymbol());
        newTransaction.setFinalPrice(buyOrder.getOfferPrice());
        newTransaction.setFinalTime(LocalDateTime.parse(formattedDateTime));
        
        transactions.save(newTransaction);
        
    }
    
    // Compare the buy and sell order offer prices; if buy order price is greater than or equal to the sell order price, returns true (vlid match)-else false (not a match)
    @Override
    public boolean matchOrders(Order buyOrder, Order sellOrder) {
        if (buyOrder.getOfferPrice().compareTo(sellOrder.getOfferPrice()) >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
}
