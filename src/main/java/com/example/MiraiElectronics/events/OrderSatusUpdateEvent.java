package com.example.MiraiElectronics.events;

import com.example.MiraiElectronics.repository.JPA.realization.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSatusUpdateEvent {
    private Order order;
}
