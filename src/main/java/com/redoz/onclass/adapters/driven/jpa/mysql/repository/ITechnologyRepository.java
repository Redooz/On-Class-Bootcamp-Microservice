package com.redoz.onclass.adapters.driven.jpa.mysql.repository;

import com.redoz.onclass.adapters.driven.jpa.mysql.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITechnologyRepository extends JpaRepository<TechnologyEntity, Long>{
    boolean existsByName(String name);
}
