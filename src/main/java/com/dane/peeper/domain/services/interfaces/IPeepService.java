package com.dane.peeper.domain.services.interfaces;

import com.dane.peeper.domain.models.Peep;

import java.util.List;
import java.util.UUID;

public interface IPeepService extends IBaseService<Peep> {
    List<Peep> findAllUserPeeps(UUID userId);
    Peep createPeep(UUID userId, Peep peep);
}
