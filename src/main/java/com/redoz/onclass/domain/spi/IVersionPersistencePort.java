package com.redoz.onclass.domain.spi;

import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.util.VersionOrderByOption;

import java.util.List;

public interface IVersionPersistencePort {
    void saveVersion(Version version);

    List<Version> findAllVersions(int page, int size, VersionOrderByOption orderBy, boolean isAsc);
}
