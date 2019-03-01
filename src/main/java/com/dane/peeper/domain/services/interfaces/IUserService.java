package com.dane.peeper.domain.services.interfaces;

import com.dane.peeper.domain.models.entities.User;

import java.util.UUID;

/**
 * Service responsible for performing user operations
 */
public interface IUserService extends IBaseService<User> {
    /**
     * Get all followers from a user
     *
     * @param id of the user
     * @return Collection of users
     * @throws Exception if the user is not found
     */
    Iterable<User> getFollowers(UUID id) throws Exception;

    /**
     * Get all following from a user
     *
     * @param id of the user
     * @return Collection of users
     * @throws Exception if the user is not found
     */
    Iterable<User> getFollowing(UUID id) throws Exception;

    /**
     * Follow a user
     *
     * @param id       of the user that will follow
     * @param followId of the user that will be followed
     * @return the followed user
     * @throws Exception if the user is not found
     */
    User followUser(UUID id, UUID followId) throws Exception;

    /**
     * Un follow a user
     *
     * @param userId   of the user that will un follow
     * @param followId of the user to un follow
     * @throws Exception if the user is not found
     */
    void unFollowUser(UUID userId, UUID followId) throws Exception;
}
