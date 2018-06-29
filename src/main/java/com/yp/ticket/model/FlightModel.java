package com.yp.ticket.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FlightModel implements Serializable {
    private Integer id;
    private String airCompany;
    private String airType;
    private String depDate;
    private String depTime;
    private String arrTime;
    private String duration;
    private String depAirport;
    private String transitAirportTitle;
    private String transitAirportCity;
    private String transitAirport;
    private String transitAirportTime;
    private String transitAirportDuration;
    private String arrAirport;
    private String ticketPriceType;
    private Double ticketPrice;
    private String ticketDiscount;
    private String ticketResource;
    private String depCity;
    private String arrCity;

    public FlightModel(){

    }
}
