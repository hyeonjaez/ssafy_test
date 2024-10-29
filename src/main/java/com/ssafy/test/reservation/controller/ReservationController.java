package com.ssafy.test.reservation.controller;

import java.util.List;

import com.ssafy.test.member.model.MemberDto;
import com.ssafy.test.reservation.model.Reservations;
import com.ssafy.test.reservation.model.service.ReservationsService;
import com.ssafy.test.resource.model.ResourceDto;
import com.ssafy.test.resource.model.service.ResourceService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reserve")
public class ReservationController {
    private final ResourceService resourceService;
    private final ReservationsService reservationsService;

    public ReservationController(ResourceService resourceService, ReservationsService reservationsService) {
        this.resourceService = resourceService;
        this.reservationsService = reservationsService;
    }

    @GetMapping
    public String mvReserve(Model model) {

        List<ResourceDto> resourceList = resourceService.getResourceList();
        System.out.println(resourceList);
        model.addAttribute("resourceList", resourceList);
        return "reserve/reservation";
    }

    @PostMapping
    public String makeReservation(Reservations reservations, Model model) {
        try {
            reservationsService.makeReservation(reservations);
            model.addAttribute("message", "예약이 성공적으로 완료되었습니다.");
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "예약 중 오류가 발생했습니다.");
            return "error/error";
        }
    }

    @GetMapping("/history")
    public String listReservations(Model model, HttpSession session) {
        MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
        List<Reservations> reservationList = reservationsService.getReservationList(memberDto.getId());
        model.addAttribute("reservationList", reservationList);
        return "reserve/reservationList";
    }

    @PostMapping("/cancel")
    public String cancelReservations(@RequestParam("reservationIds") List<Long> reservationIds) {
        reservationsService.cancelReservations(reservationIds);
        return "redirect:/reserve/history";
    }

}
