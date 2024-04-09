package com.redoz.onclass.adapters.driven.jpa.mysql.repository;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.VersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IVersionRepository extends JpaRepository<VersionEntity, Long> {
    List<VersionEntity> findAllByBootcamp_Id(Long bootcampId);
}
