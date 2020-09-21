/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.controller;

import com.sg.orderbook.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sealo
 */
@Controller
public class OrderbookErrorController implements ErrorController {

    @Autowired
    ServiceLayer service;

    @RequestMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("symbols", service.getSymbols());
        return "404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
