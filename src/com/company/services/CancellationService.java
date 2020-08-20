package com.company.services;

import com.company.models.Flight;
import com.company.models.Trip;
import com.company.models.TripStatus;
import com.company.util.CsvUtil;
import com.company.util.ModelUpdater;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/*
    Singleton class
    Handles cancellations
    1. User can cancel any booking of his trip.
    2. partial cancellation is allowed
    3. Updates all the relevant records
        a. updates trip status
        b. updates booking status
        c. updates flight seat availability
 */


public class CancellationService {

    private static CancellationService service;

    private CancellationService() {
    }

    public static CancellationService getInstance() {
        if (Objects.isNull(service)) {
            service = new CancellationService();
        }
        return service;
    }

    public Boolean cancel(Trip trip, String bookingids) throws IOException {
        ModelUpdater updater = new ModelUpdater();
        CsvUtil csvUtil = new CsvUtil();

        Arrays.stream(bookingids.split(","))
                .forEach(id -> updater.updateBookingStatus(Integer.parseInt(id), TripStatus.CANCELLED));
        updater.updateTripStatus(trip, TripStatus.PARTIAL_CANCELLED);

        Flight flight = csvUtil.getFlightById(trip.getFlightId());
        updater.updateSeats(flight.getId(), flight.getAvailableSeats() + bookingids.split(",").length);

        return true;
    }
}
