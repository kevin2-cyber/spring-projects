package com.kimikevin.web_socket_trial.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<WayPoint> wayPoints = new ArrayList<>();
    @ManyToMany(mappedBy = "routes")
    private List<Shuttle> shuttles = new ArrayList<>();
}
