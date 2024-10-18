package com.kimikevin.web_socket_trial.controller;

import com.kimikevin.web_socket_trial.model.*;
import com.kimikevin.web_socket_trial.repository.RouteRepository;
import com.kimikevin.web_socket_trial.repository.ShuttleLocationRepository;
import com.kimikevin.web_socket_trial.repository.ShuttleRepository;
import com.kimikevin.web_socket_trial.service.RouteMonitoringService;
import com.kimikevin.web_socket_trial.service.ShuttleMonitoringService;
import com.kimikevin.web_socket_trial.service.ShuttleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class ShuttleLocationController {

    private final ShuttleLocationRepository shuttleLocationRepository;
    private final  ShuttleRepository shuttleRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final ShuttleMonitoringService shuttleMonitoringService;
    private final ShuttleService shuttleService;
    private final RouteRepository routeRepository;
    private final RouteMonitoringService routeMonitoringService;

    private static final double TOLERANCE_METERS = 100.0; // Define the tolerance range (in meters)

    @PostMapping("/{shuttleId}")
    public ResponseEntity<String> updateLocation(
            @PathVariable Long shuttleId,
            @RequestBody LocationRequest locationRequest
            ) {
        Shuttle shuttle = shuttleRepository.findById(shuttleId)
                .orElseThrow(() -> new RuntimeException("Shuttle not found"));

        ShuttleLocation location = new ShuttleLocation();
        location.setShuttle(shuttle);
        location.setLongitude(locationRequest.getLongitude());
        location.setLatitude(locationRequest.getLatitude());
        location.setTimestamp(LocalDateTime.now());

        shuttleLocationRepository.save(location);

        shuttleMonitoringService.checkGeofence(location);

        Long routeId = 1L;

        // Get all routes assigned to this shuttle
        List<Route> routes = shuttleService.getShuttleRoutes(shuttleId);

        boolean isOnRoute = false;
        for (Route route : routes) {
            List<WayPoint> wayPoints = route.getWayPoints();
            if (routeMonitoringService.isShuttleOnRoute(wayPoints, locationRequest.getLatitude(), locationRequest.getLongitude(), TOLERANCE_METERS)) {
                isOnRoute = true;
                break;
            }
        }

        if (!isOnRoute) {
            // Trigger an alert or notification for route deviation
            System.out.println("Shuttle has deviated from the route!");
        } else {
            System.out.println("Shuttle is on the route.");
        }

        return ResponseEntity.ok("Location updated and route checked");
    }

    @GetMapping("/{shuttleId}/history")
    public ResponseEntity<List<ShuttleLocation>> getShuttleHistory(@PathVariable Long shuttleId) {
        return ResponseEntity.ok(shuttleLocationRepository.findByShuttleId(shuttleId));
    }

    @PostMapping("/{shuttleId}/update")
    public void sendLocationUpdate(@PathVariable Long shuttleId, @RequestBody LocationRequest locationRequest) {
        messagingTemplate.convertAndSend("/topic/location/" + shuttleId, locationRequest);
    }
}
