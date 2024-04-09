package com.redoz.onclassbootcamp.domain.api;

import com.redoz.onclassbootcamp.domain.model.Bootcamp;
import com.redoz.onclassbootcamp.domain.util.BootcampOrderByOption;

import java.util.List;

public interface IBootcampServicePort {
    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> findAllBootcamps(int page, int size, BootcampOrderByOption orderBy, boolean isAsc);

    Bootcamp findBootcampById(Long id);
}
