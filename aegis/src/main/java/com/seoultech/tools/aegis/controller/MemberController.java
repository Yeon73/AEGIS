package com.seoultech.tools.aegis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    // 회원가입 페이지 출력 요청
    @GetMapping("/aegis/member/register")
    public String registerForm() {
        return "register";
    }
}
