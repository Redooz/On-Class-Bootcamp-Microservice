package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Version;

public interface IVersionPersistencePort {
    void saveVersion(Version version);
}
