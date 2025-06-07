package com.example.MiraiElectronics.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowStockEvent {
    private Long productId;
    private String productName;
    private int currentStock;
}

