/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sealo
 */
@Controller
public class OrderbookErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "404";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
