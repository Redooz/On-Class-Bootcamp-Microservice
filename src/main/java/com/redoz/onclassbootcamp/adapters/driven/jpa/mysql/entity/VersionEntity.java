package com.redoz.onclassbootcamp.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "versions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VersionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int maxNumOfStudents;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private BootcampEntity bootcamp;
}