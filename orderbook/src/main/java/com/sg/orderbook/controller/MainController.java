/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.controller;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.entities.Transaction;
import com.sg.orderbook.repositories.OrderRepository;
import com.sg.orderbook.repositories.TransactionRepository;
import com.sg.orderbook.service.ServiceLayer;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author sealo
 */
@Controller
public class MainController {
    // index.html <-- with ticker
    // bookSymbol.html <-- book with latest information for a given symbol, like in PowerPoint example
    // history.html <-- history of all trades
    // historyForSymbol <--history of trade for symbol

    // matchOrder() <-- either bring size of order to 0
    // checks to see if it is possible to 
    // deleteOrder() <-- deletes order from order table


    @Autowired
    ServiceLayer service;

    @GetMapping("/buyOrders")
    public String buyOrders(Model model) {
        List<Order> buyOrders = service.getAllBuyOrders();
        List<Order> sellOrders = service.getAllSellOrders();
        
        model.addAttribute("buyOrders", buyOrders)
        model.addAttribute("sellOrders", sellOrders);
        return "orderbook";
    }

    @GetMapping("/sellOrders")
    public String sellOrders(Model model) {
        List<Order> orders = service.getAllSellOrders();
        model.addAttribute("orders", orders);
        return "orderbook";
    }

    @GetMapping("/tradeHistory")
    public String allTransactions(Model model) {
        List<Transaction> transactions = service.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "redirect:/history";
    }

    @GetMapping("/tradeHistoryForSymbol")
    public String transactionsBySymbol(HttpServletRequest request, Model model) {
        String symbol = request.getParameter("symbol");
        List<Transaction> transactions = service.getAllTransactionsForSymbol(symbol);
        model.addAttribute("transactions", transactions);
        return "redirect:/history";
    }

    @GetMapping("deleteOrder")
    public String deleteOrder(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUnmatchedOrder(id);
        return "orderbook";
    }

    @GetMapping("matcheOrder")
    public String matchOrder(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        service.matchOrders(id);
        return "orderbook";
    }

}
