package com.nhnacademy.airport;

public class FlightData {
    private int aircraftNo;
    private String reservationDate;
    private String departure;
    private String arrival;
    private int price;
    private String departureDetail;

    public FlightData(int aircraftNo, String reservationDate, String departure, String arrival, int price, String departureDetail) {
        this.aircraftNo = aircraftNo;
        this.reservationDate= reservationDate;
        this.departure =departure;
        this.arrival = arrival;
        this.price = price;
        this.departureDetail = departureDetail;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getPrice() {
        return price;
    }

    public String getDepartureDetail() {
        return departureDetail;
    }

    public int getAircraftNo() {
        return aircraftNo;
    }
}
