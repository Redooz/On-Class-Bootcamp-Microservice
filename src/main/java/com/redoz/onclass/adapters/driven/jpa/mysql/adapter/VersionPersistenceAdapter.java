package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.spi.IVersionPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VersionPersistenceAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    @Override
    public void saveVersion(Version version) {
        versionRepository.save(versionEntityMapper.toEntity(version));
    }
}
