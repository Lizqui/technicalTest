package com.meli.interview.back.subscription_api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;
import com.meli.interview.back.subscription_api.repository.SubscriptionRepository;
import com.meli.interview.back.subscription_api.service.SessionService;

import com.meli.interview.back.subscription_api.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SessionService sessionService;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(SessionService sessionService, SubscriptionRepository subscriptionRepository) {
        this.sessionService = sessionService;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException {        
        List<Subscription> subscriptionList = new ArrayList<>();
        // get logged user
        User loggedUser = sessionService.getLoggedUser();
        boolean isFriend = false;
        // if (loggedUser != null) {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        isFriend = user.getFriends()
                            .stream()
                            .anyMatch(e -> e.equals(loggedUser));

        /*for (User friend : user.getFriends()) {
            if (friend == loggedUser) {
                isFriend = true;
                break;
            }
        }*/

        if (isFriend) {
             subscriptionList = subscriptionRepository.findSubscriptionsByUser(user);            
        }

        float totalPrice = 0;

        for (Subscription subscription : subscriptionList) {
            totalPrice += subscription.getPrice();
        }

        return totalPrice;
        /*
          } else {
            throw new UserNotLoggedInException();
          }
         */
    }

}
