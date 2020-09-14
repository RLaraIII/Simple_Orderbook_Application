/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.repositories;

import com.sg.orderbook.entities.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Minul
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    
    List <Transaction> findByFinalSymbol(String symbol);
    
    List <Transaction> findByFinalTime(LocalDateTime finalTime);
    
    @Query("SELECT t FROM Transaction t WHERE t.buyOrder = :orderId OR t.sellOrder = :orderId")
    List<Transaction> findAllTransactionsForOrder(@Param("orderId") int orderId);
    
}
