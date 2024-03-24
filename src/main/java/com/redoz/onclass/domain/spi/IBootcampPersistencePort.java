package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Bootcamp;
import com.redoz.onclass.domain.util.BootcampOrderByOption;
import com.redoz.onclass.domain.util.CapacityOrderByOption;

import java.util.List;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);

    List<Bootcamp> findAllBootcamps(int page, int size, BootcampOrderByOption orderBy, boolean isAsc);
}
