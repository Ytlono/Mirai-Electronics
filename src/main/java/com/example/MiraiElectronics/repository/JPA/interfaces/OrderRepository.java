package com.example.MiraiElectronics.repository.JPA.interfaces;

import com.example.MiraiElectronics.repository.JPA.realization.Order;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUser(User user);
}
