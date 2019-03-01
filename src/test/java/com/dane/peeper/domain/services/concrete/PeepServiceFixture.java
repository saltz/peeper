package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IPeepRepository;
import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Assert;
import java.util.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PeepServiceFixture {

    @InjectMocks
    private PeepService service;


    @Mock
    private IPeepRepository peepRepository;
    @Mock
    private IUserRepository userRepository;

    @Before
    public void SetUp() {
        initMocks(this);
    }


    private Peep createFakePeep(UUID id) {
        return new Peep(id, "random text", Calendar.getInstance().getTime());
    }

    @Test
    public void findAll() {
        List<Peep> peeps = new ArrayList<>(Arrays.asList(createFakePeep(UUID.randomUUID()), createFakePeep(UUID.randomUUID())));

        when(peepRepository.findAll()).thenReturn(peeps);

        List<Peep> result = (List<Peep>) service.findAll();

        Assert.assertEquals(peeps, result);
        Assert.assertNotEquals(0, result.size());
        Assert.assertEquals(peeps.get(0), result.get(0));
    }

    @Test
    public void findById() throws Exception {
        Peep peep = createFakePeep(UUID.randomUUID());

        when(peepRepository.findById(peep.id)).thenReturn(Optional.of(peep));

        Peep result = service.findById(peep.id);

        Assert.assertEquals(peep, result);
    }

    @Test
    public void create() throws Exception {
        User user = new User();
        user.id = UUID.randomUUID();
        user.peeps = new ArrayList<>();
        Peep peep = createFakePeep(UUID.randomUUID());

        when(userRepository.findById(user.id)).thenReturn(Optional.of(user));
        when(peepRepository.save(peep)).thenReturn(peep);

        Peep result = service.createPeep(user.id, peep);

        Assert.assertEquals(peep, result);
    }
}