package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CapacityPersistenceAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    @Override
    public void saveCapacity(Capacity capacity) {
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

}
