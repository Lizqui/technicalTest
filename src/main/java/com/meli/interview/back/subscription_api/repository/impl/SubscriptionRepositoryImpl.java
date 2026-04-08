package com.meli.interview.back.subscription_api.repository.impl;

import org.springframework.stereotype.Repository;
import java.util.List;

import com.meli.interview.back.subscription_api.repository.SubscriptionRepository;
import com.meli.interview.back.subscription_api.dao.SubscriptionDAO;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;


@Repository
public class SubscriptionRepositoryImpl implements SubscriptionRepository {

    private final SubscriptionDAO subscriptionDAO;

    public SubscriptionRepositoryImpl(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    @Override
    public List<Subscription> findSubscriptionsByUser(User user) {
        return subscriptionDAO.findSubscriptionByUser(user);
    }
}