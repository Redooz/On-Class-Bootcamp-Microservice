package com.redoz.onclass.domain.api.usecase;

import com.redoz.onclass.domain.api.IBootcampServicePort;
import com.redoz.onclass.domain.api.IVersionServicePort;
import com.redoz.onclass.domain.exception.InvalidDateException;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.spi.IVersionPersistencePort;
import com.redoz.onclass.domain.util.VersionConstants;

public class VersionUseCase implements IVersionServicePort {
    private final IVersionPersistencePort versionPersistencePort;
    private final IBootcampServicePort bootcampServicePort;

    public VersionUseCase(IVersionPersistencePort versionPersistencePort, IBootcampServicePort bootcampServicePort) {
        this.versionPersistencePort = versionPersistencePort;
        this.bootcampServicePort = bootcampServicePort;
    }

    @Override
    public void saveVersion(Version version) {
        if (version.getEndDate().isBefore(version.getStartDate())) {
            throw new InvalidDateException(VersionConstants.INVALID_DATE_MESSAGE);
        }

        // throw exception if bootcamp not found
        bootcampServicePort.findBootcampById(version.getBootcamp().getId());


        versionPersistencePort.saveVersion(version);
    }
}
