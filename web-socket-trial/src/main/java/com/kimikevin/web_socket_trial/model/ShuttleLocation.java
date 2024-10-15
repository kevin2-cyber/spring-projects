package com.kimikevin.web_socket_trial.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShuttleLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "shuttle_id")
    private Shuttle shuttle;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
}
