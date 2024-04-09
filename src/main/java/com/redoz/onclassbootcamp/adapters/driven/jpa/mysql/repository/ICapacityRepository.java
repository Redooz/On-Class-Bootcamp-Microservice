package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.repository;

import com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity.CapacityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ICapacityRepository extends JpaRepository<CapacityEntity, Long> {
    @Query("SELECT c FROM CapacityEntity c " +
            "ORDER BY SIZE(c.technologies) DESC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountDesc(Pageable pageable);

    @Query("SELECT c FROM CapacityEntity c " +
            "ORDER BY SIZE(c.technologies) ASC")
    Page<CapacityEntity> findAllOrderByTechnologiesCountAsc(Pageable pageable);

    Optional<CapacityEntity> findByName(String name);
}
