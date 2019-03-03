package com.dane.peeper.utils;

import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.entities.User;

import java.util.Calendar;
import java.util.UUID;

public class ModelUtilities {
    public static User createFakeUser(UUID id) {
        User user = new User();
        user.id = id;
        user.firstName = "test";
        user.lastName = "test";
        return user;
    }

    public static Peep createFakePeep(UUID id) {
        return new Peep(id, "random text", Calendar.getInstance().getTime());
    }

}
