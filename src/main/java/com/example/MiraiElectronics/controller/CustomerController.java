package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.dto.UpdateUserDataDTO;
import com.example.MiraiElectronics.repository.realization.User;
import com.example.MiraiElectronics.service.CustomUserDetails;
import com.example.MiraiElectronics.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class CustomerController extends BaseController{
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getUserProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return ResponseEntity.ok(getFullUserOrThrow(userDetails));
    }

    @PutMapping
    public ResponseEntity<?> updateUserData(@Valid @RequestBody UpdateUserDataDTO updateUserDataDTO,
                                            @AuthenticationPrincipal CustomUserDetails userDetails) {
        User userUpdate = userService.updateUser(getFullUserOrThrow(userDetails), updateUserDataDTO);
//        sessionService.saveUserToSession(request, userUpdate);
        return ResponseEntity.ok(userUpdate);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.delete(getFullUserOrThrow(userDetails));
        return ResponseEntity.ok("deleted");
    }
}
