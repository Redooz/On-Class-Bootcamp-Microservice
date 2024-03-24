package com.redoz.onclass.adapters.driven.jpa.mysql.repository;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
    @Query("SELECT b FROM BootcampEntity b " +
            "ORDER BY SIZE(b.capacities) DESC")
    Page<BootcampEntity> findAllOrderByCapacityCountDesc(Pageable pageable);

    @Query("SELECT b FROM BootcampEntity b " +
            "ORDER BY SIZE(b.capacities) ASC")
    Page<BootcampEntity> findAllOrderByCapacityCountAsc(Pageable pageable);
}
