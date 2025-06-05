package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.dto.CardDTO;
import com.example.MiraiElectronics.service.CardService;
import com.example.MiraiElectronics.service.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/card")
public class CardController extends BaseController{
    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/addCard")
    public ResponseEntity<?> addPaymentMethod(@RequestBody CardDTO cardDTO, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                cardService.addCard(cardDTO,getFullUserOrThrow(userDetails))
        );
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCard(@RequestParam Long id, @AuthenticationPrincipal CustomUserDetails userDetails) {
        cardService.deleteCard(id,getFullUserOrThrow(userDetails));
        return ResponseEntity.ok("deleted");
    }

    @GetMapping
    public ResponseEntity<?> getAllUserCards(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(
                cardService.getAllUserCards(getFullUserOrThrow(userDetails))
        );
    }
}
