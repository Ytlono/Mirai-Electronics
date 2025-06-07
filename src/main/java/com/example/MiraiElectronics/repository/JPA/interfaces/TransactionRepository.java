package com.example.MiraiElectronics.repository.JPA.interfaces;

import com.example.MiraiElectronics.repository.JPA.realization.Transaction;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    @Query("SELECT c FROM Transaction c WHERE c.user = :user")
    List<Transaction> getAllUserTransactions(User user);
}
