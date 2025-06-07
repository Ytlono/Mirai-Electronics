package com.example.MiraiElectronics.repository.JPA.interfaces;

import com.example.MiraiElectronics.repository.JPA.realization.Cart;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUser(User user);

}
