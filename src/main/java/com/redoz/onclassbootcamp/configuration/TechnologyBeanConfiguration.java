package com.redoz.onclassbootcamp.configuration;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.adapter.TechnologyPersistenceAdapter;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.redoz.onclassbootcamp.domain.api.ITechnologyServicePort;
import com.redoz.onclassbootcamp.domain.api.usecase.TechnologyUseCase;
import com.redoz.onclassbootcamp.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TechnologyBeanConfiguration {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Bean
    public ITechnologyPersistencePort technologyPersistencePort() {
        return new TechnologyPersistenceAdapter(technologyRepository, technologyEntityMapper);
    }

    @Bean
    public ITechnologyServicePort technologyServicePort() {
        return new TechnologyUseCase(technologyPersistencePort());
    }
}
