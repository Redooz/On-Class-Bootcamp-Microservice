package com.redoz.onclass.adapters.driven.jpa.mysql.adapter;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import com.redoz.onclass.adapters.driven.jpa.mysql.mapper.IVersionEntityMapper;
import com.redoz.onclass.adapters.driven.jpa.mysql.repository.IVersionRepository;
import com.redoz.onclass.domain.model.Version;
import com.redoz.onclass.domain.spi.IVersionPersistencePort;
import com.redoz.onclass.domain.util.VersionOrderByOption;
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
    public List<Version> findAllVersions(int page, int size, VersionOrderByOption orderBy, boolean isAsc) {
        Sort sort = isAsc ? Sort.by(orderBy.getValue()).ascending() : Sort.by(orderBy.getValue()).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        List<VersionEntity> versionEntities = versionRepository.findAll(pageable).getContent();

        return versionEntityMapper.toModelList(versionEntities);
    }
}
