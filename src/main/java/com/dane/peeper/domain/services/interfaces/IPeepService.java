package com.dane.peeper.domain.services.interfaces;

import com.dane.peeper.domain.models.entities.Peep;

import java.util.UUID;

/**
 *  Service responsible for performing peep operations
 */
public interface IPeepService extends IBaseService<Peep> {
    /**
     * Get all peeps with supplied userId
     * @param userId the uuid of the user
     * @return collection of peeps
     */
    Iterable<Peep> findAllUserPeeps(UUID userId) throws Exception;

    /**
     * Create a peep linked to a user
     * @param userId the uuid of the user
     * @param peep the peep to save
     * @return the created peep
     */
    Peep createPeep(UUID userId, Peep peep) throws Exception;

    Peep likePeep(UUID peepId, UUID userId) throws Exception;

    void unLikePeep(UUID peepId, UUID userId) throws Exception;
 }
