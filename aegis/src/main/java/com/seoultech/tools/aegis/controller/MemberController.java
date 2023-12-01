package com.seoultech.tools.aegis.controller;

import com.seoultech.tools.aegis.dto.MemberDTO;
import com.seoultech.tools.aegis.service.MemberService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    // 생성자 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/game")
    public String game() {
        return "game";
    }

    @GetMapping("/save")
    public String saveForm() {
        return "save";
    }

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@ModelAttribute @Valid MemberDTO memberDTO, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.ok(false);
        } else {
            memberService.save(memberDTO);
            System.out.println("MemberController.save");
            System.out.println("memberDTO = " + memberDTO);
            return ResponseEntity.ok(true);
        }
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            session.setAttribute("inLog", true);
            return ResponseEntity.ok(true);
        } else {
            // login 실패
            return ResponseEntity.ok(false);
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/email-check")
    public @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }

    @GetMapping("/progress")
    public String progressForm() {
        return "progress";
    }

    @GetMapping("/achievement")
    public String achievementForm() {
        return "achievement";
    }

    @GetMapping("/profile")
    public String profileForm() {
        return "profile";
    }

    @GetMapping("/isLogin")
    @ResponseBody
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

