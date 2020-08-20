package com.company.input;

import com.company.models.Booking;
import com.company.models.Flight;
import com.company.models.Passenger;
import com.company.models.Trip;
import com.company.util.CsvUtil;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/*
    User flow:
    1. Can book tickets for people
    2. Can cancel few ticket of his trips
    3. Can view his trips

    for simplicity added the following airports only : BOM,DEL,MAA,BLR,GOI,CCU,HYD,TRV,JAI,BBI,IDR

    Users are currently linked to their user based on their email can be further extended by maintaining user sessions
 */


public class User extends Input {

    public User() {
        super();
    }

    @Override
    public void flow() throws IOException {
        Input.blankLine();
        System.out.println("Hello user please select an option number :");
        System.out.println("1. book ticket\n2. cancel ticket\n3. view bookings");
        Integer option = scanner.nextInt();

        // user can choose to book/cancel/view his trips
        switch (option) {
            case 1:
                System.out.println(book());
                break;
            case 2:
                System.out.println(cancel());
                break;
            case 3:
                System.out.println(getTrips());
                break;
            default:
                break;
        }
    }


    // GET TRIPS METHODS
    private List<Trip> getTrips() throws IOException {
        String username = getUsername();
        return new CsvUtil().getTripsByUsername(username);
    }

    // CANCELLATION CALL
    private String cancel() throws IOException {
        String username = getUsername();
        CsvUtil csvUtil = new CsvUtil();
        List<Trip> trips = csvUtil.getTripsByUsername(username);
        System.out.println("Your trips :");
        System.out.println(trips);

        System.out.println("Enter the trip you want to cancel :");
        Integer id = scanner.nextInt();
        List<Booking> bookings = csvUtil.getBookingsByTripId(id);

        System.out.println("These are the your bookings please select which booking ids need to be cancelled? (comma separated) ");
        System.out.println(bookings);
        String bookingids = scanner.next();

        cancellationService.cancel(trips.stream().filter(t -> { return t.getId().equals(id); }).findFirst().get(), bookingids);

        return "cancelled booking ids : " + bookingids;

    }

    // SEARCH AND BOOK CALL
    private String book() throws IOException {
        Input.blankLine();

        String username = getUsername();

        List<Flight> flights = search();
        Map<Integer, Flight> flightMap = new HashMap<>();

        if (flights.isEmpty()) {
            System.out.println("No flights found for this route");
            return "failed";
        }

        System.out.println("available flights please select a flight number and number of passengers in new line :");
        flights.stream().sorted((f1, f2) -> {
            return f2.getPrice() - f1.getPrice() > 0 ? 1 : 0;
        }).forEach(flight -> {
            flightMap.put(flight.getId(), flight);
            System.out.println(flight);
        });

        Input.blankLine();
        Integer flightId = scanner.nextInt();
        Integer noOfPax = scanner.nextInt();

        if (flightMap.get(flightId).getAvailableSeats() < noOfPax) {
            System.out.println("Sorry seats not available please try another.");
            return "failed";
        }

        List<Passenger> passengers = getPassengers(noOfPax);

        Trip trip = bookingService.book(flightMap.get(flightId), passengers, username);

        return "Booked successfully please find the details for reference : " + trip.toString();
    }

    /*
            UTILITY FUNCTIONS FOR THE ABOVE CALLS FOR TAKING INPUTS
     */

    private String getUsername() {
        System.out.println("Please enter your email id : ");
        String username = scanner.next();
        return username;
    }

    private List<Flight> search() throws IOException {
        System.out.println("please enter the 1. source \n2. destination \n3. date (YYYY-MM-DD)  :");
        System.out.println("Available airports : BOM,DEL,MAA,BLR,GOI,CCU,HYD,TRV,JAI,BBI,IDR");
        String src = scanner.next();
        String dst = scanner.next();
        LocalDateTime date = LocalDateTime.parse(scanner.next() + "T00:00:00");

        return searchService.getFlights(src, dst, date);
    }

    private List<Passenger> getPassengers(Integer noOfPax) {
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 0; i < noOfPax; i++) {
            System.out.println(String.format("Enter pax %s\n 1.name\n2.phone number\n3.age", i + 1));
            String name = scanner.next();
            String number = scanner.next();
            Integer age = scanner.nextInt();
            Passenger pax = new Passenger(name, number, age);
            passengers.add(pax);
        }
        return passengers;
    }
    // END OF UTILITY FUCNTIONS

}
