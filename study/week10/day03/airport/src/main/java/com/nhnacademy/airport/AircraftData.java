package com.nhnacademy.airport;

public class AircraftData {
    private int aircraftNo;
    private String kindOfAircraft;
    private String airline;

    public AircraftData(int aircraftNo, String kindOfAircraft, String airline) {
        this.aircraftNo = aircraftNo;
        this.kindOfAircraft = kindOfAircraft;
        this.airline = airline;
    }

    public int getAircraftNo() {
        return aircraftNo;
    }

    public String getKindOfAircraft() {
        return kindOfAircraft;
    }

    public String getAirline() {
        return airline;
    }

    @Override
    public String toString() {
        return "AircraftData{" +
                "aircraftNo='" + aircraftNo + '\'' +
                ", kindOfAircraft='" + kindOfAircraft + '\'' +
                ", airline='" + airline + '\'' +
                '}';
    }
}
