package com.ssafy.test.reservation.model.service;

import com.ssafy.test.reservation.model.Reservations;

import java.util.List;


public interface ReservationsService {
    void makeReservation(Reservations reservation);

    List<Reservations> getReservationList(String userId);

    void cancelReservations(List<Long> reservationIds);

    List<Reservations> getFilteredReservations(String date, Integer resourceId);

    void cancelReservationById(Long reservationId);
}