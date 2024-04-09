package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity()
@Table(name = "capacities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CapacityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String name;

    @Column(length = 90)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "capacities_technologies",
            joinColumns = @JoinColumn(name = "capacity_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private List<TechnologyEntity> technologies;

    @ManyToMany(mappedBy = "capacities")
    private List<BootcampEntity> bootcamps;
}
