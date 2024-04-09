package com.redoz.onclassbootcamp.configuration;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.adapter.CapacityPersistenceAdapter;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.redoz.onclassbootcamp.domain.api.ICapacityServicePort;
import com.redoz.onclassbootcamp.domain.api.usecase.CapacityUseCase;
import com.redoz.onclassbootcamp.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CapacityBeanConfiguration {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;
    private final TechnologyBeanConfiguration technologyBeanConfiguration;

    @Bean
    public ICapacityPersistencePort capacityPersistencePort() {
        return new CapacityPersistenceAdapter(capacityRepository, capacityEntityMapper);
    }

    @Bean
    public ICapacityServicePort capacityServicePort() {
        return new CapacityUseCase(capacityPersistencePort(), technologyBeanConfiguration.technologyServicePort());
    }
}
