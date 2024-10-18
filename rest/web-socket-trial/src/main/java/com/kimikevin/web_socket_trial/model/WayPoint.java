package com.kimikevin.web_socket_trial.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WayPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;
    private int sequence; // Sequence number to maintain waypoint order

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

}
