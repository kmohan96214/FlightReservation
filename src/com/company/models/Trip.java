package com.company.models;

import com.company.util.CsvUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*
    Trip class maintains the user trips. saved in a csv file trips.csv can be changed to DB.

    Store the following detials :
    trip id - known to the user for reference
    username - for linking user to the given trip
    name - not implemented now not storing it in passenger details only can be easily changed
    status -  ENUM can be cancelled, partially cancelled or success
    flight id - this is the flight which the trip is booked for can be extended to list of flights for connecting flights or round trip flights
    cost - total cost of the trip

    methods :
    save () - persists the trip by adding to the csv file
    toTrip() - analogous to toString() - this converts String to Trip object - basically deserializing

 */


public class Trip {
    private Integer id;
    private String username;
    private String name;
    private TripStatus status;
    private Integer flightId;
    private Double cost;
    private LocalDateTime createdAt;
    public final static String filename = "trip.csv";

    public Trip(Integer id, String username, TripStatus status, Integer flightId, Double cost) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.flightId = flightId;
        this.cost = cost;
        this.createdAt = LocalDateTime.now();
    }

    public Trip(Integer id, String username, TripStatus status, Integer flightId, Double cost, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.flightId = flightId;
        this.cost = cost;
        this.createdAt = createdAt;
    }

    public Trip save() {
        CsvUtil csvUtil = new CsvUtil();
        String tosave = String.format("%s,%s,%s,%s,%s,%s,%s", id, username, name, status, flightId, cost, createdAt);
        if (!csvUtil.write(filename, tosave)) {
            System.out.println("error in csv IO");
        }
        return this;
    }

    public static Trip toTrip(String tripString) {
        List<String> split = Arrays.asList(tripString.split(","));
        Trip trip = new Trip(Integer.parseInt(split.get(0)), split.get(1),
                Enum.valueOf(TripStatus.class, split.get(3)), Integer.parseInt(split.get(4)),
                Double.parseDouble(split.get(5)), LocalDateTime.parse(split.get(6)));
        return trip;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public Integer getFlightId() {
        return flightId;
    }

    public void setFlightId(Integer flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                ", flightId=" + flightId +
                ", cost=" + cost +
                '}';
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
