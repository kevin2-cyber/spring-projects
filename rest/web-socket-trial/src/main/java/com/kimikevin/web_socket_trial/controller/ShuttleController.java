package com.kimikevin.web_socket_trial.controller;

import com.kimikevin.web_socket_trial.model.Route;
import com.kimikevin.web_socket_trial.model.Shuttle;
import com.kimikevin.web_socket_trial.service.ShuttleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shuttles")
@RequiredArgsConstructor
public class ShuttleController {

    private final ShuttleService shuttleService;

    // assign a route to a shuttle
    @PostMapping("/{shuttleId}/routes/{routeId}")
    public ResponseEntity<Shuttle> assignRoute(@PathVariable Long shuttleId, @PathVariable Long routeId) {
        Shuttle shuttle = shuttleService.assignRouteToShuttle(shuttleId, routeId);
        return ResponseEntity.ok(shuttle);
    }

    // get all routes assigned to a shuttle
    @GetMapping("/{shuttleId}/routes")
    public ResponseEntity<List<Route>> getShuttleRoutes(@PathVariable Long shuttleId) {
        List<Route> routes = shuttleService.getShuttleRoutes(shuttleId);
        return ResponseEntity.ok(routes);
    }
}
