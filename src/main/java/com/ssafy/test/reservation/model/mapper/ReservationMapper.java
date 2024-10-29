package com.ssafy.test.reservation.model.mapper;

import java.util.List;

import com.ssafy.test.reservation.model.Reservations;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ReservationMapper {
    void insertReservation(Reservations reservations);

    List<Reservations> getAllReservations(String userId);

    void deleteReservation(Long reservationId);

    List<Reservations> getFilteredReservations(@Param("date") String date, @Param("resourceId") Integer resourceId);
}