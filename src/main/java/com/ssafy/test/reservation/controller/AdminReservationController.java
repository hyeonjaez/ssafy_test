package com.ssafy.test.reservation.controller;

import com.ssafy.test.member.model.MemberDto;
import com.ssafy.test.reservation.model.Reservations;
import com.ssafy.test.reservation.model.service.ReservationsService;
import com.ssafy.test.resource.model.ResourceDto;
import com.ssafy.test.resource.model.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/admin/reservations")
public class AdminReservationController {

    @Autowired
    private ReservationsService reservationService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping
    public String getAllReservations(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "resourceId", required = false) Integer resourceId,
            HttpSession session, Model model) {

        MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
        if (!"ADMIN".equals(memberDto.getRole())) {
            return "redirect:/main";
        }

        List<ResourceDto> resourceList = resourceService.getResourceList();
        List<Reservations> reservationList = reservationService.getFilteredReservations(date, resourceId);
        model.addAttribute("resourceList", resourceList);
        model.addAttribute("reservationList", reservationList);

        return "admin/admin";
    }

    @PostMapping("/cancel")
    public String cancelReservation(@RequestParam("reservationId") Long reservationId) {
        reservationService.cancelReservationById(reservationId);
        return "redirect:/admin/reservations";
    }
}