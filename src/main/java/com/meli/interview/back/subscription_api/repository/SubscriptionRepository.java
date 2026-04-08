package com.meli.interview.back.subscription_api.repository;

import java.util.List;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;

public interface SubscriptionRepository {
    List<Subscription> findSubscriptionsByUser(User user);
}