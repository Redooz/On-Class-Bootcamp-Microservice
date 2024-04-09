package com.redoz.onclassbootcamp.domain.api;

import com.redoz.onclassbootcamp.domain.model.Version;
import com.redoz.onclassbootcamp.domain.util.VersionOrderByOption;

import java.util.List;

public interface IVersionServicePort {
    void saveVersion(Version version);

    List<Version> findAllVersions(int page, int size, VersionOrderByOption orderBy, boolean isAsc, long bootcampId);
}
