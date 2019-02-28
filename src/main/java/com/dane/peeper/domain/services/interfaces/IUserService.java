package com.dane.peeper.domain.services.interfaces;

import com.dane.peeper.domain.models.entities.User;

import java.util.List;
import java.util.UUID;

/**
 *
 */
public interface IUserService extends IBaseService<User> {
    List<User> getFollowers(UUID id);
    List<User> getFollowing(UUID id);

    User followUser(UUID id, UUID followId);
    void unFollowUser(UUID userId, UUID followId);
}
