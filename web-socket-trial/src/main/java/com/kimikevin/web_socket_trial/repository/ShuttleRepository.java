package com.kimikevin.web_socket_trial.repository;

import com.kimikevin.web_socket_trial.model.Shuttle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShuttleRepository extends JpaRepository<Shuttle, Long> {}
