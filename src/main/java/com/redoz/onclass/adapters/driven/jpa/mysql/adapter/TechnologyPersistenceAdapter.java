package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ITechnologyEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ITechnologyRepository;
import com.redoz.onclass.domain.model.Technology;
import com.redoz.onclass.domain.spi.ITechnologyPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
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

    @Override
    public List<Technology> findAllTechnologies(int page, int size, boolean isAsc) {
        Sort sort = isAsc ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<Technology> technologies = technologyEntityMapper.toModelList(technologyRepository.findAll(pageable).getContent());

        if (technologies.isEmpty()) {
            throw new NoDataFoundException();
        }

        return technologies;
    }


}