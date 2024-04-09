package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.CapacityEntity;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.redoz.onclassbootcamp.domain.model.Capacity;
import com.redoz.onclassbootcamp.domain.spi.ICapacityPersistencePort;
import com.redoz.onclassbootcamp.domain.util.CapacityOrderByOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class CapacityPersistenceAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    @Override
    public void saveCapacity(Capacity capacity) {
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

    @Override
    public List<Capacity> findAllCapacities(int page, int size, CapacityOrderByOption orderBy, boolean isAsc) {
        if (orderBy == CapacityOrderByOption.TECHNOLOGY_COUNT) {
            Sort sort = Sort.by(orderBy.getValue());
            Pageable pageable = PageRequest.of(page, size, sort);

            List<CapacityEntity> capacityEntities =
                    isAsc ? capacityRepository.findAllOrderByTechnologiesCountAsc(pageable).getContent() :
                            capacityRepository.findAllOrderByTechnologiesCountDesc(pageable).getContent();
            return capacityEntityMapper.toModelList(capacityEntities);
        }

        Sort sort = isAsc ? Sort.by(orderBy.getValue()).ascending() : Sort.by(orderBy.getValue()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<CapacityEntity> capacityEntities = capacityRepository.findAll(pageable).getContent();

        return capacityEntityMapper.toModelList(capacityEntities);
    }

    @Override
    public Optional<Capacity> findCapacityByName(String name) {
        return capacityRepository.findByName(name).map(capacityEntityMapper::toModel);
    }
}
