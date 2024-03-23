package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Bootcamp;

public interface IBootcampPersistencePort {
    void saveBootcamp(Bootcamp bootcamp);
}
