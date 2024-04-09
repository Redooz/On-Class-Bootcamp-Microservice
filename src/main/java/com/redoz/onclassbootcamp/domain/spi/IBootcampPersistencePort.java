package com.redoz.onclassbootcamp.domain.spi;

import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.util.BootcampOrderByOption;

import java.util.List;
import java.util.Optional;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> findAllBootcamps(int page, int size, BootcampOrderByOption orderBy, boolean isAsc);

    Optional<Bootcamp> findBootcampById(Long id);
}
