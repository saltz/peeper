package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IPeepRepository;
import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.exceptions.PeepNotFoundException;
import com.dane.peeper.domain.exceptions.UserNotFoundException;
import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.services.interfaces.IPeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.UUID;

@Service
public class PeepService implements IPeepService {

    private final IPeepRepository peepRepository;
    private final IUserRepository userRepository;

    @Autowired
    public PeepService(IPeepRepository peepRepository, IUserRepository userRepository) {
        this.peepRepository = peepRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<Peep> findAll() {
        return peepRepository.findAll();
    }

    @Override
    public Peep findById(UUID id) throws Exception {
        return peepRepository.findById(id).orElseThrow(() -> (new PeepNotFoundException("no peep exists with the supplied id")));
    }

    @Override
    public Peep create(Peep object) {
        return null;
    }

    @Override
    public Peep hardUpdate(Peep object) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        //TODO validate weather its the owner or a admin
        peepRepository.deleteById(id);
    }

    @Override
    public Iterable<Peep> findAllUserPeeps(UUID userId) throws Exception {
        User user =  userRepository.findById(userId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        return user.peeps;
    }

    @Override
    public Peep createPeep(UUID userId, Peep peep) throws UserNotFoundException {
        peep.date = Calendar.getInstance().getTime();

        User user = userRepository.findById(userId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));
        user.addPeep(peep);
        user = userRepository.save(user);

        return user.peeps.get(user.peeps.size() - 1);
    }

    @Override
    public Peep likePeep(UUID peepId, UUID userId) throws Exception {
        Peep peep = peepRepository.findById(peepId).orElseThrow(() -> (new PeepNotFoundException("no peep exists with the supplied id")));
        User user = userRepository.findById(userId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));

        peep.likes.add(user);
        peepRepository.save(peep);

        return peep;
    }

    @Override
    public void unLikePeep(UUID peepId, UUID userId) throws Exception {
        Peep peep = peepRepository.findById(peepId).orElseThrow(() -> (new PeepNotFoundException("no peep exists with the supplied id")));
        User user = userRepository.findById(userId).orElseThrow(() -> (new UserNotFoundException("no user exists with the supplied id")));

        peep.likes.remove(user);
        peepRepository.save(peep);
    }
}
