package com.kimikevin.web_socket_trial.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LocationRequest {
    private double latitude;
    private double longitude;
}
