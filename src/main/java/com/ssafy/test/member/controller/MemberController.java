package com.ssafy.test.member.controller;

import java.util.Map;

import com.ssafy.test.member.model.MemberDto;
import com.ssafy.test.member.model.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    @GetMapping("/{userid}")
    @ResponseBody
    public String idCheck(@PathVariable("userid") String userId) throws Exception {
        logger.debug("idCheck userid : {}", userId);
        int cnt = memberService.idCheck(userId);
        return cnt + "";
    }

    @PostMapping("/join")
    public String join(MemberDto memberDto, Model model) {
        logger.debug("memberDto info : {}", memberDto);
        try {
            memberService.joinMember(memberDto);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "회원 가입 중 문제 발생!!!");
            return "error/error";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> map,
                        @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session,
                        HttpServletResponse response) {
        logger.debug("login map : {}", map);
        try {
            MemberDto memberDto = memberService.loginMember(map);
            if (memberDto != null) {
                session.setAttribute("userinfo", memberDto);
                Cookie cookie = new Cookie("ssafy_id", map.get("userId"));
                cookie.setPath("/");
                if ("ok".equals(saveid)) {
                    cookie.setMaxAge(60 * 60 * 24 * 365);
                } else {
                    cookie.setMaxAge(0);
                }
                response.addCookie(cookie);
                return "redirect:/";
            } else {
                model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
                return "index";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "로그인 중 문제 발생!!!");
            return "error/error";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
