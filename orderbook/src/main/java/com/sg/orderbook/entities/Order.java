/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Minul
 */
@Entity
public class Order {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderid")
    private int id;
    
    @Column
    private int size;
    
    @Column
    private boolean side; // buy - 1 sell - 0
    
    @Column
    private LocalDateTime time;
    
    @Column
    private boolean active; // active - 1 inavtice - 0
    
    @Column
    private BigDecimal offerPrice;
    
    @Column
    private String symbol;
    
}
