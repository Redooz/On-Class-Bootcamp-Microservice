package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.util.OrderByOption;

import java.util.List;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> findAllBootcamps(int page, int size, OrderByOption orderBy, boolean isAsc);
}
