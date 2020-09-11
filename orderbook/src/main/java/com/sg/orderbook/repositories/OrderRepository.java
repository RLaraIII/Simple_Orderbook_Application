/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.repositories;

import com.sg.orderbook.entities.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Minul
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    @Query("SELECT * FROM order WHERE active = 1")
//    List findAllBySide();
    
    @Query("SELECT o FROM Order o WHERE o.size > 1")
    List<Order> findAllActiveOrders();
    
    @Query("SELECT o FROM Order o WHERE o.side = 1")
    List<Order> findAllBuyOrders();
    
    @Query("SELECT o FROM Order o WHERE o.side = 0")
    List<Order> findAllSellOrders();
}
