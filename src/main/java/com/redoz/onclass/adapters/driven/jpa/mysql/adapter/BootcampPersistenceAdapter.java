package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.IBootcampEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.IBootcampRepository;
import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.spi.IBootcampPersistencePort;

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
}
