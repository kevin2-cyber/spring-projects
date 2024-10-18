package com.kimikevin.web_socket_trial.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {
    private String name;
    private List<WayPointRequest> waypoints;
}
