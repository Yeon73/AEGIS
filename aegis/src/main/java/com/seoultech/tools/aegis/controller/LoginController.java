package com.seoultech.tools.aegis.controller;

import com.seoultech.tools.aegis.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST})
public class LoginController {
    private final MemberService memberService;

    // 생성자 주입
    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/isLogin")
    public ResponseEntity<Boolean> checkLoginStatus(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("inLog");

        if (isLoggedIn != null && isLoggedIn) {
            // 로그인 상태이면 true 반환
            return ResponseEntity.ok(true);
        } else {
            // 비로그인 상태이면 false 반환
            return ResponseEntity.ok(false);
        }
    }
}
