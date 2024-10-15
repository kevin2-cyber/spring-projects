package com.kimikevin.web_socket_trial.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shuttle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String licensePlate;

    @ManyToMany
    @JoinTable(
            name = "shuttle_route",
            joinColumns = @JoinColumn(name = "shuttle_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id")
    )
    @ToString.Exclude
    private List<Route> routes = new ArrayList<>();
}
