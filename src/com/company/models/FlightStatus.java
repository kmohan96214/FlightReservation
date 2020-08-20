package com.company.models;

/*
- Active (A) - A flight goes to this status when we receive the actual time of a gate pushback or takeoff. While active, we track information about departure, estimated arrival, and where available, positional data.

- Unknown (U) - A flight goes to this status when we cannot determine the actual arrival time (landing or gate arrival) in a reasonable amount of time. Note, this does not indicate an error or that there was any problem or incident with the flight, only that no data was available for the actual landing time.

 - Redirected (R) - The flight has changed its destination to an unscheduled airport. After landing at an unscheduled airport, the state will change to Diverted.

- Landed (L) - The flight landed at the scheduled airport.

- Diverted (D) - The flight landed at an unscheduled airport.

- Cancelled (C) - The flight was cancelled.
 */

public enum FlightStatus {
    ACTIVE, UNKNOWN, LANDED, DIVERTED, REDIRECTED, CANCELLED
}
