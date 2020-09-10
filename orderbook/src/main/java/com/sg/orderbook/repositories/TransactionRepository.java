/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.repositories;

import com.sg.orderbook.entities.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Minul
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
//    @Query("SELECT * FROM transaction WHERE symbol = ?")
//    List findAllTransactionsForSymbol(String symbol);
}
