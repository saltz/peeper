package com.dane.peeper.domain.services.concrete;

import com.dane.peeper.data.repositories.interfaces.IPeepRepository;
import com.dane.peeper.data.repositories.interfaces.IUserRepository;
import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.services.concretes.PeepService;
import com.dane.peeper.utils.ModelUtilities;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class peepServiceTest {

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



    @Test
    public void findAll() {
        List<Peep> peeps = new ArrayList<>(Arrays.asList(ModelUtilities.createFakePeep(UUID.randomUUID()), ModelUtilities.createFakePeep(UUID.randomUUID())));

        when(peepRepository.findAll()).thenReturn(peeps);

        List<Peep> result = service.findAll();

        Assert.assertEquals(peeps, result);
        Assert.assertNotEquals(0, result.size());
        Assert.assertEquals(peeps.get(0), result.get(0));
    }

    @Test
    public void findById() throws Exception {
        Peep peep = ModelUtilities.createFakePeep(UUID.randomUUID());

        when(peepRepository.findById(peep.id)).thenReturn(Optional.of(peep));

        Peep result = service.findById(peep.id);

        Assert.assertEquals(peep, result);
    }
}