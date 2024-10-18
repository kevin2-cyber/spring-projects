package com.kimikevin.web_socket_trial.repository;

import com.kimikevin.web_socket_trial.model.ShuttleLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShuttleLocationRepository extends JpaRepository<ShuttleLocation, Long> {
  List<ShuttleLocation> findByShuttleId(Long shuttleId);
}