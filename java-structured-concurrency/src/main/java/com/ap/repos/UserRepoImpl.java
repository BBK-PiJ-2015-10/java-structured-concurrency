package com.ap.repos;

import com.ap.dtos.Email;
import com.ap.dtos.User;
import com.ap.dtos.UserId;
import com.ap.dtos.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class UserRepoImpl implements UserRepo {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public User findUserByIdPort(UserId userId) {
        logger.info("Finding user with id {}", userId.userId());
        //throw new RuntimeException("Error finding user with id '%s'".formatted(userId));
        try {
            delay(Duration.ofMillis(1500));
        } catch (InterruptedException e) {
            //throw new RuntimeException(e);
        }
        User user = new User(new Username("alex"), userId, new Email("alexis.gmail.com"));
        logger.info("Found user with id {}", user.userId().userId());
        return user;
    }
}
