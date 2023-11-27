package com.seoultech.tools.aegis.controller;

import com.seoultech.tools.aegis.dto.MemberDTO;
import com.seoultech.tools.aegis.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MemberController {
    // 생성자 주입
    private final MemberService memberService;

    @GetMapping("/member/game")
    public String game() {
        return "game";
    }

    // 회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() { return "login"; }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("isLogin", true);
            return "main";
        } else {
            // login 실패
            return "login";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @GetMapping("/member/progress")
    public String progressForm() {
        return "progress";
    }

    @GetMapping("/member/achievement")
    public String achievementForm() {
        return "achievement";
    }

    @GetMapping("/member/profile")
    public String profileForm() {
        return "profile";
    }

    @PostMapping("/member/start-game")
    public String startGame(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLogin");

        if (isLoggedIn != null && isLoggedIn) {
            // 로그인 상태이면 game 페이지로 이동
            return "redirect:/member/game";
        } else {
            // 비로그인 상태이면 login 페이지로 리다이렉트
            return "redirect:/member/login";
        }
    }
}
