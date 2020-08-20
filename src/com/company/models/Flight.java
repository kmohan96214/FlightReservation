package com.company.models;

import com.company.util.CsvUtil;
import com.company.util.ModelUpdater;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/*
    Flight store the flight details
    Can be extended to International and national flights by creating interface
    Available seats is updated every time someone either books or cancels a ticket

    New flights can be added to the csv flights.csv or by logging as an Admin and creating in the console
 */

public class Flight {
    private Integer id;
    private Integer airlineId;
    private String src;
    private String dst;
    private LocalDateTime departureTime;
    private String duration;
    private Integer seats;
    private Integer availableSeats;
    private FlightStatus status;
    private Double price;
    public static final String filename = "flights.csv";

    public Flight() {
    }

    private Flight(Integer id, Integer airlineId, String src, String dst, LocalDateTime departureTime, String duration, Integer seats, Integer availableSeats, FlightStatus status, Double price) {
        this.id = id;
        this.airlineId = airlineId;
        this.src = src;
        this.dst = dst;
        this.departureTime = departureTime;
        this.duration = duration;
        this.seats = seats;
        this.availableSeats = availableSeats;
        this.status = status;
        this.price = price;
    }

    public Flight save() {
        String toSave = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", id, airlineId, src, dst, departureTime, duration, seats, availableSeats, status.toString(), price );
        CsvUtil csvUtil = new CsvUtil();
        if (!csvUtil.write(filename, toSave)) {
            System.out.println("failed due to IO error");
        }
        return this;
    }

    public static Flight fromString(String fs) {
        List<String> attributes = Arrays.asList(fs.split(","));
        return new Flight(Integer.parseInt(attributes.get(0)), Integer.parseInt(attributes.get(1)),
                attributes.get(2), attributes.get(3),
                LocalDateTime.parse(attributes.get(4)), attributes.get(5), Integer.parseInt(attributes.get(6))
                , Integer.parseInt(attributes.get(7)), Enum.valueOf(FlightStatus.class, attributes.get(8)),
                Double.parseDouble(attributes.get(9)));
    }

    public static Boolean updateFlightSeats(Integer flightId, Integer newSeats) throws IOException {
        ModelUpdater updater = new ModelUpdater();
        return updater.updateSeats(flightId, newSeats);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(Integer airlineId) {
        this.airlineId = airlineId;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineId=" + airlineId +
                ", src='" + src + '\'' +
                ", dst='" + dst + '\'' +
                ", departureTime=" + departureTime +
                ", duration='" + duration + '\'' +
                ", seats='" + seats + '\'' +
                ", availableSeats='" + availableSeats + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
