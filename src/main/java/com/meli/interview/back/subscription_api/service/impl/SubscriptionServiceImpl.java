package com.meli.interview.back.subscription_api.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.meli.interview.back.subscription_api.dao.SubscriptionDAO;
import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.session.UserSession;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;
import com.meli.interview.back.subscription_api.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{

    @Override
    public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException {
        ArrayList<Subscription> subscriptionList = new ArrayList<Subscription>();

        // get logged user
        User loggedUser = UserSession.getInstance().getLoggedUser();
        boolean isFriend = false;
        if (loggedUser != null) {
            for (User friend : user.getFriends()) {
                if (friend == loggedUser) {
                    isFriend = true;
                    break;
                }
            }
            if (isFriend) {
                subscriptionList = SubscriptionDAO.findSubscriptionByUser(user);
            }

            float totalPrice = 0;

            for (Subscription subscription : subscriptionList) {
                totalPrice += subscription.getPrice();
            }

            return totalPrice;
        } else {
            throw new UserNotLoggedInException();
        }
    }
  
}

