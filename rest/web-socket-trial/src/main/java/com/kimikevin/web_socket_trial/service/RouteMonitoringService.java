package com.kimikevin.web_socket_trial.service;

import com.kimikevin.web_socket_trial.model.WayPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteMonitoringService {

    private static final int EARTH_RADIUS = 6371; // Radius in KM

    // Haversine formula to calculate distance between two points
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c * 1000; // return distance in meters
    }

    // Check if the shuttle is near a waypoint (within a specified tolerance)
    public boolean isNearWaypoint(WayPoint waypoint, double shuttleLat, double shuttleLon, double toleranceMeters) {
        double distance = calculateDistance(waypoint.getLatitude(), waypoint.getLongitude(), shuttleLat, shuttleLon);
        return distance <= toleranceMeters;
    }

    // Route monitoring logic
    public boolean isShuttleOnRoute(List<WayPoint> waypoints, double shuttleLat, double shuttleLon, double toleranceMeters) {
        for (WayPoint waypoint : waypoints) {
            if (isNearWaypoint(waypoint, shuttleLat, shuttleLon, toleranceMeters)) {
                return true; // Shuttle is near a valid waypoint
            }
        }
        return false; // Shuttle has deviated from the route
    }
}
