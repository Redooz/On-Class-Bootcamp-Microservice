package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.exception.TechnologyAlreadyExistsException;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TechnologyAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public void saveTechnology(Technology technology) {
        if (technologyRepository.existsByName(technology.getName())) {
            throw new TechnologyAlreadyExistsException();
        }
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }
}
