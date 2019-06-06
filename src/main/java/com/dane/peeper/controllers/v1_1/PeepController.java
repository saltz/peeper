package com.dane.peeper.controllers.v1_1;

import com.dane.peeper.controllers.abstractions.ResourceBaseController;
import com.dane.peeper.domain.extensions.BindingResultExtension;
import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.requestModels.PeepRequestModel;
import com.dane.peeper.domain.models.resourceModels.PeepResource;
import com.dane.peeper.domain.models.viewModels.PeepViewModel;
import com.dane.peeper.domain.services.interfaces.IPeepService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("peep_controller_v1.1")
public class PeepController extends ResourceBaseController<PeepResource> {

    private final IPeepService service;
    private final ModelMapper mapper;

    @Autowired
    public PeepController(IPeepService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "v1.1/peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity getAll() {
        List<PeepViewModel> response = mapper.map(service.findAll(), new TypeToken<List<PeepViewModel>>() {
        }.getType());
        return returnResourceCollection(convertToResourceCollection(response), HttpStatus.OK);
    }

    @GetMapping(path = "v1.1/peeps/{id}", produces = "application/json")
    public @ResponseBody
    HttpEntity getById(@PathVariable UUID id) throws Exception {
        PeepViewModel result = mapper.map(service.findById(id), PeepViewModel.class);
        return new ResponseEntity<>(new PeepResource(result), HttpStatus.OK);
    }

    @GetMapping(path = "v1.1/users/{userId}/peeps", produces = "application/json")
    public @ResponseBody
    HttpEntity getAllUserPeeps(@PathVariable UUID userId) throws Exception {
        List<PeepViewModel> response = mapper.map(service.findAllUserPeeps(userId), new TypeToken<List<PeepViewModel>>() {
        }.getType());
        return returnResourceCollection(convertToResourceCollection(response), HttpStatus.OK);
    }

    @PostMapping(path = "v1.1/users/{userId}/peeps", produces = "application/json", consumes = "application/json")
    public @ResponseBody
    HttpEntity createPeep(@PathVariable UUID userId, @RequestBody @Valid PeepRequestModel peep, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(BindingResultExtension.returnBindingErrorMessages(bindingResult), HttpStatus.BAD_REQUEST);
        }

        PeepViewModel response = mapper.map(service.createPeep(userId, mapper.map(peep, Peep.class)), PeepViewModel.class);
        return new ResponseEntity<>(new PeepResource(response), HttpStatus.CREATED);
    }

    @PostMapping(path = "v1.1/peeps/{peepId}/like/{userId}")
    public @ResponseBody
    HttpEntity likePeep(@PathVariable UUID peepId, @PathVariable UUID userId) throws Exception {
        PeepViewModel response = mapper.map(service.likePeep(peepId, userId), PeepViewModel.class);
        return new ResponseEntity<>(new PeepResource(response), HttpStatus.CREATED);
    }

    private List<PeepResource> convertToResourceCollection(List<PeepViewModel> viewModels) {
        return viewModels.stream().map(p -> {
            try {
                return new PeepResource(p);
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
    }
}
