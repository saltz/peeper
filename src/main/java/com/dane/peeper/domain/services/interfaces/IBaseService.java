package com.dane.peeper.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

public interface IBaseService<C> {
    List<C> findAll();
    C findById(UUID id);
    C create(C object);
    C hardUpdate(C object);
    void deleteById(UUID id);
}
