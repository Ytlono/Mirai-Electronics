package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.dto.AuthRequest;
import com.example.MiraiElectronics.dto.RegisterDTO;
import com.example.MiraiElectronics.dto.response.AuthResponse;
import com.example.MiraiElectronics.service.auth.AuthService;
import com.example.MiraiElectronics.service.auth.ConfirmationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@SessionAttributes("pendingUser")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final ConfirmationService confirmationService;

    public AuthController(AuthService authService, ConfirmationService confirmationService) {
        this.authService = authService;
        this.confirmationService = confirmationService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterDTO registerRequest, HttpSession session) {
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Пароль не может быть пустым"));
        }

        confirmationService.sendConfirmationEmail(registerRequest.getEmail());
        session.setAttribute("pendingUser", registerRequest);

        return ResponseEntity.ok(Map.of("message", "Код подтверждения отправлен на email", "email", registerRequest.getEmail()));
    }

    @PostMapping("/confirmEmail")
    public ResponseEntity<?> confirmEmail(@RequestParam String email, @RequestParam String code, @SessionAttribute("pendingUser") RegisterDTO pendingUser) {
        if (!confirmationService.isConfirmed(email, code))
            return ResponseEntity.ok("discard");

        confirmationService.removeConfirmedEmail(email);
        authService.register(pendingUser);
        return ResponseEntity.ok(pendingUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate (
            @RequestBody AuthRequest authRequest) {
        System.out.println( "It is called" );
        return ResponseEntity.ok(authService.login(authRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("message", "Logout successful"));
    }
}
