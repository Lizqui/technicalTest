package com.meli.interview.back.subscription_api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.meli.interview.back.subscription_api.exception.UserNotLoggedInException;
import com.meli.interview.back.subscription_api.repository.SubscriptionRepository;
import com.meli.interview.back.subscription_api.service.SessionService;
import com.meli.interview.back.subscription_api.subscription.Subscription;
import com.meli.interview.back.subscription_api.user.User;

@ExtendWith(MockitoExtension.class) 
class SubscriptionServiceImplTest {

    @Mock
    private SessionService sessionService; 

    @Mock
    private SubscriptionRepository subscriptionRepository; 

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService; 

    private User loggedUser;
    private User friendUser;

    @BeforeEach
    void setUp() {
        loggedUser = new User();
        loggedUser.setId("1");
        loggedUser.setName("Angie");

        friendUser = new User();
        friendUser.setId("2");
        friendUser.setName("Carlos");
    }

    @Test
    void getUserSubscriptionsCost_WhenUsersAreFriends_ShouldReturnTotalCost() throws Exception {
        friendUser.addFriend(loggedUser);         
        Subscription netflix = new Subscription("netflix"); 
        Subscription spotify = new Subscription("spotify"); 
        List<Subscription> carlosSubscriptions = Arrays.asList(netflix, spotify);
        
        when(sessionService.getLoggedUser()).thenReturn(loggedUser);
        when(subscriptionRepository.findSubscriptionsByUser(friendUser)).thenReturn(carlosSubscriptions);
        Float totalCost = subscriptionService.getUserSubscriptionsCost(friendUser);
        assertEquals(250f, totalCost);
    }

    @Test
    void getUserSubscriptionsCost_WhenUserIsNotLoggedIn_ShouldThrowUserNotLoggedInException() {
        when(sessionService.getLoggedUser()).thenReturn(null);

        assertThrows(UserNotLoggedInException.class,
                () -> subscriptionService.getUserSubscriptionsCost(friendUser));
    }

    @Test
    void getUserSubscriptionsCost_WhenUsersAreNotFriends_ShouldReturnZero() {
        // friendUser no tiene a loggedUser en su lista de amigos
        when(sessionService.getLoggedUser()).thenReturn(loggedUser);

        Float totalCost = subscriptionService.getUserSubscriptionsCost(friendUser);
        assertEquals(0f, totalCost);
    }
}