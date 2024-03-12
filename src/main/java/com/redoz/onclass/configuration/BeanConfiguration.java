package com.redoz.onclass.configuration;

import com.redoz.onclass.adapters.driven.jpa.mysql.adapter.TechnologyAdapter;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.redoz.onclass.domain.api.ITechnologyServicePort;
import com.redoz.onclass.domain.api.usecase.TechnologyUseCase;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
