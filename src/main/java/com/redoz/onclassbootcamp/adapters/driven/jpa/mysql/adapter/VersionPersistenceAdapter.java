package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.redoz.onclassbootcamp.domain.model.Version;
import com.redoz.onclassbootcamp.domain.spi.IVersionPersistencePort;
import com.redoz.onclassbootcamp.domain.util.VersionOrderByOption;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class VersionPersistenceAdapter implements IVersionPersistencePort {
    private final IVersionRepository versionRepository;
    private final IVersionEntityMapper versionEntityMapper;

    @Override
    public void saveVersion(Version version) {
        versionRepository.save(versionEntityMapper.toEntity(version));
    }

    @Override
    public List<Version> findAllVersions(int page, int size, VersionOrderByOption orderBy, boolean isAsc, long bootcampId) {
        if (bootcampId > 0) {
            List<VersionEntity> versionEntities = versionRepository.findAllByBootcamp_Id(bootcampId);
            return versionEntityMapper.toModelList(versionEntities);
        }

        Sort sort = isAsc ? Sort.by(orderBy.getValue()).ascending() : Sort.by(orderBy.getValue()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<VersionEntity> versionEntities = versionRepository.findAll(pageable).getContent();

        return versionEntityMapper.toModelList(versionEntities);
    }
}
