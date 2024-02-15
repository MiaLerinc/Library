package com.legend.library.service;

import com.legend.library.dao.MemberRepository;
import com.legend.library.model.BookType;
import com.legend.library.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findById(int id) {
        Optional<Member> result = memberRepository.findById(id);

        Member member = null;

        if (result.isPresent()) {
            member = result.get();
        }
        else {
            throw new RuntimeException("Did not find member id - " + id);
        }
        return member;
    }

    @Override
    public List<Member> findMembersByFilterText(String filterText) {
        return memberRepository.findMembersByFilterText(filterText);
    }

}
