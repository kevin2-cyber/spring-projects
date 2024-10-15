package com.kimikevin.web_socket_trial.repository;

import com.kimikevin.web_socket_trial.model.WayPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WayPointRepository extends JpaRepository<WayPoint, Long> {
  List<WayPoint> findByRouteIdOrderBySequenceAsc(Long routeId);
}