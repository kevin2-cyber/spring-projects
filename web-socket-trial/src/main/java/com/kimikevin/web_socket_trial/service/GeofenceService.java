package com.kimikevin.web_socket_trial.service;

import com.kimikevin.web_socket_trial.model.Geofence;
import org.springframework.stereotype.Service;

@Service
public class GeofenceService {
    private static final int EARTH_RADIUS = 6371;

    public double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lngDistance = Math.toRadians(lng2 - lng1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c * 1000;
    }

    public boolean isWithinGeofence(double shuttleLat, double shuttleLon, Geofence geofence) {
        double distance = calculateDistance(shuttleLat, shuttleLon, geofence.getLatitude(), geofence.getLongitude());
        return distance <= geofence.getRadiusInMeters();
    }
}
