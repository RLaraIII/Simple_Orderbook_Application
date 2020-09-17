/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.controller;

import com.sg.orderbook.entities.Order;
import com.sg.orderbook.entities.Transaction;
import com.sg.orderbook.service.ServiceLayer;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/")
    public String viewIndex(HttpServletRequest request, Model model) {
        model.addAttribute("symbols", service.getSymbols());
        model.addAttribute("transactions", service.getTop5ByFinalDate());

        return "index";
    }

    @GetMapping("/orderbook")
    public String viewOrderbook(HttpServletRequest request, Model model) {
        String symbol = request.getParameter("symbol").toUpperCase();

        if (symbol == null) {
            return "redirect:/";
        }

        if (service.getSymbols().contains(symbol)) {
            service.findPotentialTransactions(symbol);
        } else {
            return "redirect:/symbolnotfound?symbol=" + symbol;
        }

        List<Order> buyOrders = service.getAllBuyOrdersForSymbol(symbol);
        List<Order> sellOrders = service.getAllSellOrdersForSymbol(symbol);

        model.addAttribute("symbol", symbol);
        model.addAttribute("symbols", service.getSymbols());
        model.addAttribute("buyOrders", buyOrders);
        model.addAttribute("sellOrders", sellOrders);
        return "orderbook";
    }

    @GetMapping("/symbolnotfound")
    public String symbolNotFound(HttpServletRequest request, Model model) {
        String symbol = request.getParameter("symbol").toUpperCase();
        model.addAttribute("symbol", symbol);

        return "symbolnotfound";
    }

    @GetMapping("/neworderbook")
    public String newOrderBook(HttpServletRequest request, Model model) {
        String symbol = request.getParameter("symbol").toUpperCase();

        service.createOrderbook(symbol);

        return "redirect:/orderbook?symbol=" + symbol;
    }

    @GetMapping("/tradehistory")
    public String transactionsBySymbol(HttpServletRequest request, Model model) {
        String symbol = request.getParameter("symbol").toUpperCase();

        if (symbol == null) {
            return "redirect:/";
        } else if (!service.getSymbols().contains(symbol)) {
            return "redirect:/symbolnotfound?symbol=" + symbol;
        }

        List<Transaction> transactions = service.getAllTransactionsForSymbol(symbol);

        model.addAttribute("symbol", symbol);
        model.addAttribute("symbols", service.getSymbols());
        model.addAttribute("transactions", transactions);
        return "history";
    }

    @GetMapping("/deleteorder")
    public String deleteOrder(Integer id, String symbol) {
        service.deleteUnmatchedOrder(id);
        return "redirect:/orderbook?symbol=" + symbol;
    }

    @GetMapping("matchorder")
    public String matchOrder(Integer orderId, String symbol) {
        service.matchOrders(orderId);
        return "redirect:/orderbook?symbol=" + symbol;
    }

    @PostMapping("createorder")
    public String addOrder(HttpServletRequest request) {
        int size = Integer.parseInt(request.getParameter("size"));
        String symbol = request.getParameter("symbol");
        boolean side = Boolean.parseBoolean(request.getParameter("side"));
        BigDecimal offerPrice = new BigDecimal(request.getParameter("offerPrice"));

        Order createdOrder = new Order();
        createdOrder.setSize(size);
        createdOrder.setOfferPrice(offerPrice);
        createdOrder.setActive(true);
        createdOrder.setTime(LocalDateTime.now());
        createdOrder.setSide(side);
        createdOrder.setSymbol(symbol);

        service.addOrder(createdOrder);

        return "redirect:/orderbook?symbol=" + symbol;
    }

}
