package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.dto.OrderRequest;
import com.example.MiraiElectronics.dto.OrderStateUpdateDTO;
import com.example.MiraiElectronics.service.CustomUserDetails;
import com.example.MiraiElectronics.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController{
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<?> getUserOrders(@AuthenticationPrincipal CustomUserDetails userDetails){
        return ResponseEntity.ok(orderService.getUserOrders(
                getFullUserOrThrow(userDetails))
        );
    }
    
    @GetMapping("/getOrder")
    public ResponseEntity<?> getOrderById(@RequestParam Long orderId,
                                          @AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(
                orderService.getOrderByIdAndUserId(
                        orderId, getFullUserOrThrow(userDetails))
        );
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseEntity<?> order = orderService.makeOrder(orderRequest, getFullUserOrThrow(userDetails));
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
    
    @DeleteMapping
    public ResponseEntity<?> cancelOrder(@RequestParam Long orderId,
                                         @AuthenticationPrincipal CustomUserDetails userDetails) {
        orderService.cancelOrder(orderId,  getFullUserOrThrow(userDetails));
        return ResponseEntity.ok(Map.of("message", "Заказ отменен"));
    }

    @PutMapping("/update-status")
    public ResponseEntity<?> updateOrderState(@RequestBody OrderStateUpdateDTO orderStateUpdateDTO){
        return ResponseEntity.ok(orderService.updateOrderStatus(orderStateUpdateDTO.getOrderId(),orderStateUpdateDTO.getStatus()));
    }

}
