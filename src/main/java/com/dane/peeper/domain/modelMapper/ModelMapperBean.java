package com.dane.peeper.domain.modelMapper;

import com.dane.peeper.domain.modelMapper.converters.ToUserViewModelConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperBean {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new ToUserViewModelConverter());
        return mapper;
    }
}
