package com.meli.interview.back.subscription_api.service;

import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.user.User;

public interface SubscriptionService {
    

    /**
     * Devuelve el costo total de las suscripciones de un usuario siempre que el usuario que esté logueado
     * se encuentre en su lista de amigos
     * @param user
     * @return costo total de la suscripciones del user
     * @throws UserNotLoggedInException si no hay un usuario logueado
     */
    public Float getUserSubscriptionsCost(User user) throws UserNotLoggedInException;
}
