package com.ssafy.test.reservation.model.service;

import java.util.List;

import com.ssafy.test.reservation.model.Reservations;
import com.ssafy.test.reservation.model.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReservationsServiceImpl implements ReservationsService {
    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    public void makeReservation(Reservations reservations) {
        reservationMapper.insertReservation(reservations);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Reservations> getReservationList(String userId) {
        return reservationMapper.getAllReservations(userId);
    }

    @Override
    public void cancelReservations(List<Long> reservationIds) {
        for (Long reservationId : reservationIds) {
            reservationMapper.deleteReservation(reservationId);
        }
    }

    @Transactional(readOnly = true)
    public List<Reservations> getFilteredReservations(String date, Integer resourceId) {
        return reservationMapper.getFilteredReservations(date, resourceId);
    }

    public void cancelReservationById(Long reservationId) {
        reservationMapper.deleteReservation(reservationId);
    }
}
