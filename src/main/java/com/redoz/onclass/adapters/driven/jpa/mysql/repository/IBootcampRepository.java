package com.redoz.onclass.adapters.driven.jpa.mysql.repository;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.BootcampEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBootcampRepository extends JpaRepository<BootcampEntity, Long> {
}
