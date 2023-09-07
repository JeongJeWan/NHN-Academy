package com.nhnacademy.jpa.repository;


import com.nhnacademy.jpa.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
