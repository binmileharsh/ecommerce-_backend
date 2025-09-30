package com.example.storeelectronic.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class projectconfig {
    @Bean
    public ModelMapper modelmapper(){
        return new ModelMapper();
    }
}
