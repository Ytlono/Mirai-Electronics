package com.example.MiraiElectronics.service.order;

import com.example.MiraiElectronics.repository.JPA.interfaces.OrderItemRepository;
import com.example.MiraiElectronics.repository.JPA.realization.CartItem;
import com.example.MiraiElectronics.repository.JPA.realization.Order;
import com.example.MiraiElectronics.repository.JPA.realization.OrderItem;
import com.example.MiraiElectronics.service.cart.CartItemService;
import com.example.MiraiElectronics.service.core.GenericEntityService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemService extends GenericEntityService<OrderItem,Long> {
    private final CartItemService cartItemService;

    public OrderItemService(OrderItemRepository orderItemRepository, CartItemService cartItemService) {
        super(orderItemRepository);
        this.cartItemService = cartItemService;
    }

    public List<OrderItem> convertCartItemsToOrderItems(List<Long> ids, Order order) {
        List<CartItem> cartItems = cartItemService.getAllById(ids);
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = OrderItem.builder()
                    .product(cartItem.getProduct())
                    .quantity(cartItem.getQuantity())
                    .unitPrice(cartItem.getProduct().getPrice())
                    .totalPrice(cartItem.getPrice())
                    .order(order)
                    .build();
            orderItems.add(orderItem);
        }
        return orderItems;
    }
}
