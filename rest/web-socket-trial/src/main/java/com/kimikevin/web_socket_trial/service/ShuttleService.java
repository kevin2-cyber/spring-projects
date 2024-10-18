package com.kimikevin.web_socket_trial.service;

import com.kimikevin.web_socket_trial.model.Route;
import com.kimikevin.web_socket_trial.model.Shuttle;
import com.kimikevin.web_socket_trial.repository.RouteRepository;
import com.kimikevin.web_socket_trial.repository.ShuttleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShuttleService {
    private final ShuttleRepository shuttleRepository;
    private final RouteRepository routeRepository;

    public Shuttle assignRouteToShuttle(Long shuttleId, Long routeId) {
        Shuttle shuttle = shuttleRepository.findById(shuttleId)
                .orElseThrow(() -> new RuntimeException("Shuttle not found"));
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        shuttle.getRoutes().add(route);
        route.getShuttles().add(shuttle);

        return shuttleRepository.save(shuttle);
    }

    public List<Route> getShuttleRoutes(Long shuttleId) {
        Shuttle shuttle = shuttleRepository.findById(shuttleId)
                .orElseThrow(() -> new RuntimeException("Shuttle not found"));
        return shuttle.getRoutes();
    }
}
