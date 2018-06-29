package com.yp.ticket.service;

import com.yp.ticket.model.FlightModel;

import java.util.List;

public interface FlightService {

    List<FlightModel> getZNFlight(String depCity, String arrCity, String depDate) throws Exception;

    List<FlightModel> getInterFlight(String depCity, String arrCity, String depDate) throws Exception;

    String translate(String content) throws Exception;

    String identyImg(String path) throws Exception;
}
