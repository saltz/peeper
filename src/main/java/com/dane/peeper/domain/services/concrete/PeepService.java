package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IPeepRepository;
import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.models.Peep;
import com.dane.peeper.domain.models.User;
import com.dane.peeper.domain.services.interfaces.IPeepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
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
    public List<Peep> findAll() {
        return (List<Peep>) peepRepository.findAll();
    }

    @Override
    public Peep findById(UUID id) {
        return peepRepository.findById(id).orElse(null);
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
        peepRepository.deleteById(id);
    }

    @Override
    public List<Peep> findAllUserPeeps(UUID userId) {
        User user =  userRepository.findById(userId).orElse(null);
        return user.peeps;
    }

    @Override
    public Peep createPeep(UUID userId, Peep peep) {
        peep.date = Calendar.getInstance().getTime();
        User user = userRepository.findById(userId).orElse(null);

        if(user == null) {
            return null;
        }
        user.addPeep(peep);

        userRepository.save(user);

        return peep;
    }
}
