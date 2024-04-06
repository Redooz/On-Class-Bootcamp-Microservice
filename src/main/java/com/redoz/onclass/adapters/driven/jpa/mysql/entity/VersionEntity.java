package com.redoz.onclass.adapters.driven.jpa.mysql.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    private Date startDate;

    private Date endDate;

    private int numOfStudents;

    @ManyToOne
    @JoinColumn(name = "bootcamp_id")
    private BootcampEntity bootcamp;
}
