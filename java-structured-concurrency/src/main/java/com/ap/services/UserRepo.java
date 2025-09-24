package com.ap.services;

import com.ap.dtos.User;
import com.ap.dtos.UserId;

public interface UserRepo {

    User findUserByIdPort(UserId userId);

}
