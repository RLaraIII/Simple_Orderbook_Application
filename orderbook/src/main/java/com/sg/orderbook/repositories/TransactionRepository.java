/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.orderbook.repositories;

import com.sg.orderbook.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Minul
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{
    
}