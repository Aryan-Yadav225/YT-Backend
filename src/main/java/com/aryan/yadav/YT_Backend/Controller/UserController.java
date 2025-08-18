package com.aryan.yadav.YT_Backend.Controller;

import com.aryan.yadav.YT_Backend.Service.UserRegistrationService;
import com.aryan.yadav.YT_Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserService userService;

    @GetMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();

        return userRegistrationService.registerUser(jwt.getTokenValue());
    }

    @PostMapping("subscribe/{userId}")
    public boolean subscribeUser(@PathVariable String userId) {
        userService.  subscribeUser(userId);
        return true;
    }

    @PostMapping("unsubscribe/{userId}")
    public boolean unsubscribeUser(@PathVariable String userId) {
        userService.unsubscribeUser(userId);
        return true;
    }
}
