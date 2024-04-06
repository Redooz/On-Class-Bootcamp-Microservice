package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.util.BootcampOrderByOption;

import java.util.List;
import java.util.Optional;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> findAllBootcamps(int page, int size, BootcampOrderByOption orderBy, boolean isAsc);

    Optional<Bootcamp> findBootcampById(Long id);
}
