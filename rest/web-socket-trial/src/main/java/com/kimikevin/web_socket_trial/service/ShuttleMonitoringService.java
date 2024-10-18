package com.kimikevin.web_socket_trial.service;

import com.kimikevin.web_socket_trial.model.Geofence;
import com.kimikevin.web_socket_trial.model.ShuttleLocation;
import com.kimikevin.web_socket_trial.repository.GeofenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShuttleMonitoringService {
    private final GeofenceService geofenceService;
    private final GeofenceRepository geofenceRepository;
    private final NotificationService notificationService;

    public void checkGeofence(ShuttleLocation shuttleLocation) {
        List<Geofence> geofences = geofenceRepository.findAll();

        for (Geofence geofence : geofences) {
            boolean isWithin = geofenceService.isWithinGeofence(shuttleLocation.getLatitude(), shuttleLocation.getLongitude(), geofence);
            if (isWithin) {
                // Trigger entry action if the shuttle enters the geofence
                notificationService.notifyStudents("Shuttle entered geofence: " + geofence.getName());
            } else {
                // Trigger exit action if the shuttle leaves the geofence
                notificationService.notifyStudents("Shuttle exited geofence: " + geofence.getName());
            }
        }
    }
}
