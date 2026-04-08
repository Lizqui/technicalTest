package com.meli.interview.back.subscription_api.dao;

import com.meli.interview.back.subscription_api.exception.CollaboratorCallException;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SubscriptionDAO {

    public ArrayList<Subscription> findSubscriptionByUser(User user) {
        throw new CollaboratorCallException(
            "TripDAO should not be invoked on an unit test.");
    }
}
