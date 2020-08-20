package com.company.util;

import com.company.models.Booking;
import com.company.models.Flight;
import com.company.models.Trip;
import com.company.models.TripStatus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

/*
    Utility class for persisting the updated model data
    1. persisting seats
    2. booking status
    3. trip status
 */

public class ModelUpdater {

    public Boolean updateSeats(Integer flightId, Integer seats) throws IOException {
        File file = new File(Flight.filename);
        List<String> lines = Files.readAllLines(file.toPath());

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Integer id = Integer.parseInt(line.split(",")[0]);
            if (flightId.equals(id)) {
                String[] newline = line.split(",");
                newline[7] = seats.toString();
                lines.set(i, String.join(",", newline));
            }
        }

        Files.write(file.toPath(), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        return true;
    }

    public Boolean updateBookingStatus(Integer id, TripStatus status) {

        try {
            File file = new File(Booking.filename);
            List<String> bookings = Files.readAllLines(file.toPath());

            for (int i = 0; i < bookings.size(); i++) {
                String booking = bookings.get(i);
                Integer bookingId = Integer.parseInt(booking.split(",")[0]);
                if (id.equals(bookingId)) {
                    String[] newline = booking.split(",");
                    newline[5] = status.toString();
                    bookings.set(i, String.join(",", newline));
                }
            }

            Files.write(file.toPath(), bookings, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException exception) {
            System.out.println("Failed to cancel booking\n");
            return false;
        }
    }

    public Boolean updateTripStatus(Trip trip, TripStatus status) throws IOException {
        File file = new File(Trip.filename);
        List<String> lines = Files.readAllLines(file.toPath());

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            Integer id = Integer.parseInt(line.split(",")[0]);
            if (trip.getId().equals(id)) {
                String[] newline = line.split(",");
                newline[3] = status.toString();
                lines.set(i, String.join(",", newline));
            }
        }

        Files.write(file.toPath(), lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        return true;
    }

}
