package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.repository.JPA.realization.User;
import com.example.MiraiElectronics.service.CustomUserDetails;

public abstract class BaseController {
    protected User getFullUserOrThrow(CustomUserDetails userDetails) {
        return userDetails.getUser();
    }
}
