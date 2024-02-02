package com.legend.library.service;

import com.legend.library.model.Member;

import java.util.List;


public interface MemberService {

    List<Member> findAll();
    void addMember(Member member);

    Member findById(int id);

    List<Member> findMembersByFilterText(String filterText);
}
