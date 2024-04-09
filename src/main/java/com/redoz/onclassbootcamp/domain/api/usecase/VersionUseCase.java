package com.redoz.onclassbootcamp.domain.api.usecase;

import com.redoz.onclassbootcamp.domain.api.IBootcampServicePort;
import com.redoz.onclassbootcamp.domain.api.IVersionServicePort;
import com.redoz.onclassbootcamp.domain.exception.InvalidDateException;
import com.redoz.onclassbootcamp.domain.exception.NoDataFoundException;
import com.redoz.onclassbootcamp.domain.model.Version;
import com.redoz.onclassbootcamp.domain.spi.IVersionPersistencePort;
import com.redoz.onclassbootcamp.domain.util.DomainConstants;
import com.redoz.onclassbootcamp.domain.util.VersionConstants;
import com.redoz.onclassbootcamp.domain.util.VersionOrderByOption;

import java.util.List;

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

    @Override
    public List<Version> findAllVersions(int page, int size, VersionOrderByOption orderBy, boolean isAsc, long bootcampId) {
        List<Version> versions = versionPersistencePort.findAllVersions(page, size, orderBy, isAsc, bootcampId);

        if (versions.isEmpty()) {
            throw new NoDataFoundException(DomainConstants.NO_DATA_FOUND_VERSION_EXCEPTION_MESSAGE);
        }

        return versions;
    }
}
