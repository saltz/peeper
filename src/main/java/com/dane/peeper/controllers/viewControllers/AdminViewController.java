package com.dane.peeper.controllers.viewControllers;

import com.dane.peeper.domain.models.viewModels.DashboardViewModel;
import com.dane.peeper.domain.models.viewModels.PeepViewModel;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import com.dane.peeper.domain.services.interfaces.IPeepService;
import com.dane.peeper.domain.services.interfaces.IUserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
public class AdminViewController {
    private final ModelMapper mapper;
    private final IUserService userService;
    private final IPeepService peepService;

    @Autowired
    public AdminViewController(ModelMapper mapper, IUserService userService, IPeepService peepService) {
        this.mapper = mapper;
        this.userService = userService;
        this.peepService = peepService;
    }

    @GetMapping("/admin")
    public String getAdminView() {
        return "admin/login";
    }

    @GetMapping("/admin/dashboard")
    public ModelAndView getDashboard() {
        ModelAndView mav = new ModelAndView("admin/dashboard");

        DashboardViewModel data = new DashboardViewModel();
        data.userAmount = userService.findAll().size();
        data.peepAmount = peepService.findAll().size();

        mav.addObject("data", data);

        return mav;
    }

    @GetMapping("/admin/users")
    public ModelAndView getAdminUsers() {
        ModelAndView mav = new ModelAndView("admin/users");
        mav.addObject("users", mapper.map(userService.findAll(), new TypeToken<List<UserViewModel>>() {
        }.getType()));

        return mav;
    }

    @GetMapping("/admin/users/{id}")
    public ModelAndView getAdminUser(@PathVariable UUID id) throws Exception {
        ModelAndView mav = new ModelAndView("admin/users");
        mav.addObject("users", mapper.map(userService.findById(id), UserViewModel.class));

        return mav;
    }

    @GetMapping("/admin/peeps")
    public ModelAndView getAdminPeeps() {
        ModelAndView mav = new ModelAndView("admin/peeps");
        mav.addObject("peeps", mapper.map(peepService.findAll(), new TypeToken<List<PeepViewModel>>(){}.getType()));

        return mav;
    }
}
