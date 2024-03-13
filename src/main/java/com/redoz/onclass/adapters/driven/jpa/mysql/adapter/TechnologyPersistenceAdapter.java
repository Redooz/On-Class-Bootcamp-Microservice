package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements ITechnologyPersistencePort {
    private final ITechnologyRepository technologyRepository;
    private final ITechnologyEntityMapper technologyEntityMapper;

    @Override
    public void saveTechnology(Technology technology) {
        technologyRepository.save(technologyEntityMapper.toEntity(technology));
    }

    @Override
    public Optional<Technology> findTechnologyByName(String name) {
        return technologyRepository.findByName(name).map(technologyEntityMapper::toModel);
    }


}
