package com.seoultech.tools.aegis.repository;

import com.seoultech.tools.aegis.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
