package com.meli.interview.back.subscription_api.service.impl;

import org.springframework.stereotype.Service;
import com.meli.interview.back.subscription_api.service.SessionService;
import com.meli.interview.back.subscription_api.user.User;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public User getLoggedUser() {
        throw new UnsupportedOperationException("Real auth not implemented — use mock in tests");
    }
}