package com.meli.interview.back.subscription_api.controller;

import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.service.SubscriptionService;
import com.meli.interview.back.subscription_api.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final SubscriptionService subscriptionService;

    public UserController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }
 
    @GetMapping("/{userId}/subscriptions/cost")
    public ResponseEntity<Float> getUserSubscriptionsCost(@PathVariable String userId) {
        User user = new User();
        user.setId(userId); 
        
        Float cost = subscriptionService.getUserSubscriptionsCost(user);
        return ResponseEntity.ok(cost);
    }
    
    @ExceptionHandler(UserNotLoggedInException.class)
    public ResponseEntity<Void> handleUserNotLoggedIn(UserNotLoggedInException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}