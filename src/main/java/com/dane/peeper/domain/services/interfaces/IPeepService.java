package com.dane.peeper.domain.services.interfaces;

import com.dane.peeper.domain.models.entities.Peep;

import java.util.List;
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
    List<Peep> findAllUserPeeps(UUID userId);

    /**
     * Create a peep linked to a user
     * @param userId the uuid of the user
     * @param peep the peep to save
     * @return the created peep
     */
    Peep createPeep(UUID userId, Peep peep);
}
