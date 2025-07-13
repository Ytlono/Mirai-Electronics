package com.example.MiraiElectronics.service.payment;

import com.example.MiraiElectronics.repository.JPA.interfaces.TransactionRepository;
import com.example.MiraiElectronics.repository.JPA.realization.Transaction;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import com.example.MiraiElectronics.repository.repositoryEnum.TransactionType;
import com.example.MiraiElectronics.service.core.GenericEntityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService extends GenericEntityService<Transaction,Long> {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactions(User user){
        return transactionRepository.getAllUserTransactions(user);
    }

    @Transactional
    public void createTransaction(User user, BigDecimal amount, String description, TransactionType type){
        Transaction transaction = Transaction.builder()
                .user(user)
                .type(type)
                .amount(amount)
                .description(description)
                .build();
        save(transaction);
    }
}
