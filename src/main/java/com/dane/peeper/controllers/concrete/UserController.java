package com.dane.peeper.controllers.concrete;

import com.dane.peeper.controllers.abstracts.BaseController;
import com.dane.peeper.domain.models.User;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "users")
public class UserController extends BaseController<User, IUserService> {

    @Autowired
    public UserController(IUserService service) {
        super(service);
    }
}
