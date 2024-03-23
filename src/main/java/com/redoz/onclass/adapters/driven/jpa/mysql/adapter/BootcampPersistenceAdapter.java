package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.spi.IBootcampPersistencePort;
import com.redoz.onclass.domain.util.OrderByOption;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BootcampPersistenceAdapter implements IBootcampPersistencePort {
    private final IBootcampRepository bootcampRepository;
    private final IBootcampEntityMapper bootcampEntityMapper;

    public BootcampPersistenceAdapter(IBootcampRepository bootcampRepository, IBootcampEntityMapper bootcampEntityMapper) {
        this.bootcampRepository = bootcampRepository;
        this.bootcampEntityMapper = bootcampEntityMapper;
    }

    @Override
    public void saveBootcamp(Bootcamp bootcamp) {
        bootcampRepository.save(bootcampEntityMapper.toEntity(bootcamp));
    }

    @Override
    public List<Bootcamp> findAllBootcamps(int page, int size, OrderByOption orderBy, boolean isAsc) {
        if (orderBy == OrderByOption.TECHNOLOGY_COUNT) {
            Sort sort = Sort.by(orderBy.getValue());
            Pageable pageable = PageRequest.of(page, size, sort);

            List<BootcampEntity> bootcamps = isAsc ? bootcampRepository.findAllOrderByCapacityCountAsc(pageable).getContent() :
                    bootcampRepository.findAllOrderByCapacityCountDesc(pageable).getContent();

            return bootcampEntityMapper.toModelList(bootcamps);
        }

        Sort sort = isAsc ? Sort.by(orderBy.getValue()).ascending() : Sort.by(orderBy.getValue()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        List<BootcampEntity> bootcamps = bootcampRepository.findAll(pageable).getContent();

        return bootcampEntityMapper.toModelList(bootcamps);

    }
}
