package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.dto.TopUpBalanceDTO;
import com.example.MiraiElectronics.service.auth.CustomUserDetails;
import com.example.MiraiElectronics.service.payment.PaymentService;
import com.example.MiraiElectronics.service.payment.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController extends BaseController {
    private final PaymentService paymentService;
    private final TransactionService transactionService;

    public PaymentController(PaymentService paymentService, TransactionService transactionService) {
        this.paymentService = paymentService;
        this.transactionService = transactionService;
    }

    @PostMapping("/top-up")
    public ResponseEntity<?> topUpBalance(@RequestBody TopUpBalanceDTO dto,
                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                "Balance topped up successfully. New balance: " +
                paymentService.topUpUserBalanceFromCard(
                dto.getCardId(), dto.getSum(), getFullUserOrThrow(userDetails))
        );
    }


    @GetMapping("/history")
    public ResponseEntity<?> getTransactionHistory(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                transactionService.getAllTransactions(
                        getFullUserOrThrow(userDetails))
        );
    }
}

