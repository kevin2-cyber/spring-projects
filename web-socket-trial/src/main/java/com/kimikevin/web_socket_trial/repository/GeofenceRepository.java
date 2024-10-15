package com.kimikevin.web_socket_trial.repository;

import com.kimikevin.web_socket_trial.model.Geofence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeofenceRepository extends JpaRepository<Geofence, Long> {}
