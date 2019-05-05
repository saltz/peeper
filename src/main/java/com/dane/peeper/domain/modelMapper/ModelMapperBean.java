package com.dane.peeper.domain.modelMapper;

import com.dane.peeper.domain.models.entities.Peep;
import com.dane.peeper.domain.models.entities.User;
import com.dane.peeper.domain.models.requestModels.PeepRequestModel;
import com.dane.peeper.domain.models.requestModels.UserRequestModel;
import com.dane.peeper.domain.models.viewModels.PeepViewModel;
import com.dane.peeper.domain.models.viewModels.UserViewModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperBean {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        mapper.createTypeMap(UserRequestModel.class, User.class);
        mapper.createTypeMap(User.class, UserViewModel.class);

        mapper.createTypeMap(PeepRequestModel.class, Peep.class);
        mapper.createTypeMap(Peep.class, PeepViewModel.class);

        return mapper;
    }
}
