package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.ICapacityEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.ICapacityRepository;
import com.redoz.onclass.domain.model.Capacity;
import com.redoz.onclass.domain.spi.ICapacityPersistencePort;
import com.redoz.onclass.domain.util.OrderByOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class CapacityPersistenceAdapter implements ICapacityPersistencePort {
    private final ICapacityRepository capacityRepository;
    private final ICapacityEntityMapper capacityEntityMapper;

    @Override
    public void saveCapacity(Capacity capacity) {
        capacityRepository.save(capacityEntityMapper.toEntity(capacity));
    }

    @Override
    public List<Capacity> findAllCapacities(int page, int size, OrderByOption orderBy, boolean isAsc) {
        Sort sort = isAsc ? Sort.by(orderBy.getValue()).ascending() : Sort.by(orderBy.getValue()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return capacityEntityMapper.toModelList(capacityRepository.findAll(pageable).getContent());
    }
}
