package com.kimikevin.web_socket_trial.controller;

import com.kimikevin.web_socket_trial.model.Route;
import com.kimikevin.web_socket_trial.model.RouteRequest;
import com.kimikevin.web_socket_trial.model.WayPoint;
import com.kimikevin.web_socket_trial.repository.RouteRepository;
import com.kimikevin.web_socket_trial.repository.WayPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {
    private final RouteRepository routeRepository;
    private final WayPointRepository wayPointRepository;

    @PostMapping
    public ResponseEntity<Route> createRoute(@RequestBody RouteRequest request) {
        Route route = new Route();
        route.setName(request.getName());

        List<WayPoint> wayPoints = request.getWaypoints()
                .stream()
                .map(wp -> {
                    WayPoint wayPoint = new WayPoint();
                    wayPoint.setLongitude(wp.getLongitude());
                    wayPoint.setLatitude(wp.getLatitude());
                    wayPoint.setSequence(wp.getSequence());
                    wayPoint.setRoute(route);
                    return wayPoint;
                })
                .collect(Collectors.toList());

        route.setWayPoints(wayPoints);
        routeRepository.save(route);
        return ResponseEntity.ok(route);
    }
}
