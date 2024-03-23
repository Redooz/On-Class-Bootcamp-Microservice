package com.redoz.onclass.configuration;

import com.redoz.onclass.adapters.driven.jpa.mysql.adapter.BootcampPersistenceAdapter;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.redoz.onclass.domain.api.IBootcampServicePort;
import com.redoz.onclass.domain.api.usecase.BootcampUseCase;
import com.redoz.onclass.domain.spi.IBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BootcampBeanConfiguration {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;
    private final CapacityBeanConfiguration capacityBeanConfiguration;

    @Bean
    public IBootcampPersistencePort bootcampPersistencePort() {
        return new BootcampPersistenceAdapter(bootcampRepository, bootcampEntityMapper);
    }

    @Bean
    public IBootcampServicePort bootcampServicePort() {
        return new BootcampUseCase(bootcampPersistencePort(), capacityBeanConfiguration.capacityServicePort());
    }
}