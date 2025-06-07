package com.example.MiraiElectronics.repository.JPA.interfaces;

import com.example.MiraiElectronics.repository.JPA.realization.Card;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card,Long> {
    @Query("SELECT c FROM Card c WHERE c.user = :user")
    List<Card> getAllUserCards(User user);
}
