package com.seoultech.tools.aegis.service;

import com.seoultech.tools.aegis.dto.MemberDTO;
import com.seoultech.tools.aegis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void register(MemberDTO memberDTO) {
    }
}
