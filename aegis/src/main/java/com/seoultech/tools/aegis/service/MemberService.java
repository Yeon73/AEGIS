package com.seoultech.tools.aegis.service;

import com.seoultech.tools.aegis.dto.MemberDTO;
import com.seoultech.tools.aegis.entity.MemberEntity;
import com.seoultech.tools.aegis.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void register(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 register 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        // repository의 register 메서드 호출 (조건: entity 객체를 넘겨줘야 함)
    }
}
