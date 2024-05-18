package com.redoz.onclassbootcamp.configuration;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.adapter.VersionPersistenceAdapter;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.redoz.onclassbootcamp.domain.api.IVersionServicePort;
import com.redoz.onclassbootcamp.domain.api.usecase.VersionUseCase;
import com.redoz.onclassbootcamp.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class VersionBeanConfiguration {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;
    private final BootcampBeanConfiguration bootcampBeanConfiguration;

    @Bean
    public IVersionPersistencePort versionPersistencePort() {
        return new VersionPersistenceAdapter(versionRepository, versionEntityMapper);
    }

    @Bean
    public IVersionServicePort versionServicePort() {
        return new VersionUseCase(versionPersistencePort(), bootcampBeanConfiguration.bootcampServicePort());
    }
}