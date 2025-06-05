package com.example.MiraiElectronics.controller;

import com.example.MiraiElectronics.repository.realization.User;
import com.example.MiraiElectronics.service.CustomUserDetails;

public abstract class BaseController {
    protected User getFullUserOrThrow(CustomUserDetails userDetails) {
        return userDetails.getUser();
    }
}
