package com.redoz.onclass.configuration;

import com.redoz.onclass.adapters.driven.jpa.mysql.adapter.CapacityPersistenceAdapter;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.redoz.onclass.domain.api.ICapacityServicePort;
import com.redoz.onclass.domain.api.usecase.CapacityUseCase;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
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
