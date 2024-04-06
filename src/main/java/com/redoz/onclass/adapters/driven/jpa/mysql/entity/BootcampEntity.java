package com.redoz.onclass.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bootcamps")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BootcampEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String name;

    @Column(length = 90)
    private String description;

    @ManyToMany
    @JoinTable(
            name = "bootcamps_capacities",
            joinColumns = @JoinColumn(name = "bootcamp_id"),
            inverseJoinColumns = @JoinColumn(name = "capacity_id")
    )
    private List<CapacityEntity> capacities;

    @OneToMany(mappedBy = "bootcamp")
    private List<VersionEntity> versions;
}
