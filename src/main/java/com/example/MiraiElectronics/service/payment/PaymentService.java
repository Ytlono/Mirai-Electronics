package com.example.MiraiElectronics.service.payment;

import com.example.MiraiElectronics.repository.JPA.realization.Card;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import com.example.MiraiElectronics.repository.repositoryEnum.TransactionType;
import com.example.MiraiElectronics.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final CardService cardService;
    private final UserService userService;
    private final TransactionService transactionService;

    public PaymentService(CardService cardService, UserService userService, TransactionService transactionService) {
        this.cardService = cardService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    public boolean isPayed() {
        return true;
    }

    @Transactional
    public BigDecimal topUpUserBalanceFromCard(Long cardId, BigDecimal sum, User user) {
        Card card = cardService.findById(cardId);

        user.setBalance(
                user.getBalance().add(
                        cardService.withdrawFromCard(cardId,sum,user))
        );

        userService.save(user);
        transactionService.createTransaction(user,sum,"top-Up balance sum:" + String.valueOf(sum),TransactionType.TOP_UP);
        return user.getBalance();
    }

    @Transactional
    public void refundToUser(BigDecimal amount, User user) {
        user.setBalance(user.getBalance().add(amount));
        userService.save(user);
    }
}

