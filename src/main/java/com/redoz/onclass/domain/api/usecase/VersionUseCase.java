package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.IVersionServicePort;
import com.redoz.onclass.domain.exception.InvalidDateException;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.spi.IVersionPersistencePort;
import com.redoz.onclass.domain.util.VersionConstants;

public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort) {
        this.versionPersistencePort = versionPersistencePort;
    }

    @Override
    public void saveVersion(Version version) {
        if (version.getEndDate().isBefore(version.getStartDate())) {
            throw new InvalidDateException(VersionConstants.INVALID_DATE_MESSAGE);
        }

        versionPersistencePort.saveVersion(version);
    }
}
