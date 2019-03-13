package com.dane.peeper.domain.modelMapper.converters;

import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import org.modelmapper.AbstractConverter;

public class ToUserViewModelConverter extends AbstractConverter<User, UserViewModel> {
    @Override
    protected UserViewModel convert(User source) {
        UserViewModel userViewModel = new UserViewModel();
        userViewModel.id = source.id;
        userViewModel.alias = source.alias;
        userViewModel.biography = source.biography;
        userViewModel.email = source.email;
        userViewModel.firstName = source.firstName;
        userViewModel.lastName = source.lastName;
        userViewModel.picture = source.picture;
        userViewModel.website = source.website;
        userViewModel.role = source.role;

        return userViewModel;
    }
}
