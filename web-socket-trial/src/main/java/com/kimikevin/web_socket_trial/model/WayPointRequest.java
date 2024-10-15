package com.kimikevin.web_socket_trial.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WayPointRequest {
    private double latitude;
    private double longitude;
    private int sequence;
}
