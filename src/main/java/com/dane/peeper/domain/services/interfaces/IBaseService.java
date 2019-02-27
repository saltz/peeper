package com.dane.peeper.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

/**
 * Base service responsible for all CRUD operations for the supplied type
 * @param <C> type for CRUD operations
 */
public interface IBaseService<C> {
    /**
     * Find all of type
     * @return collection of type
     */
    List<C> findAll();

    /**
     * Find type by id
     * @param id uuid of the type
     * @return single of type
     */
    C findById(UUID id);

    /**
     * Save instance of type
     * @param object object to save
     * @return the saved instance of the type
     */
    C create(C object);

    /**
     * Update the whole instance of the type
     * @param object new updated object of type
     * @return the saved instance of the type
     */
    C hardUpdate(C object);

    /**
     * Delete object by id
     * @param id the uuid of the object
     */
    void deleteById(UUID id);
}
