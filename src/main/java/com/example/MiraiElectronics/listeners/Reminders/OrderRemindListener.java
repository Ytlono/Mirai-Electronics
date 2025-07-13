package com.example.MiraiElectronics.listeners.Reminders;

import com.example.MiraiElectronics.events.OrderSatusUpdateEvent;
import com.example.MiraiElectronics.repository.JPA.realization.User;
import com.example.MiraiElectronics.service.cart.CartItemService;
import com.example.MiraiElectronics.service.notifications.JavaMailSenderService;
import com.example.MiraiElectronics.service.order.OrderService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderRemindListener extends BaseReminderListener{
    private final OrderService orderService;

    public OrderRemindListener(CartItemService cartItemService, JavaMailSenderService mailSenderService, OrderService orderService) {
        super(cartItemService, mailSenderService);
        this.orderService = orderService;
    }

    @EventListener
    public void OnOrderStatusUpdate(OrderSatusUpdateEvent event){
        User user = event.getOrder().getUser();
        mailSenderService.send(
                user.getEmail(),
                "Изменение статуса заказа",
                String.format("Статус заказа обновлен до: %s",
                        event.getOrder().getStatus())
        );
    }
}
