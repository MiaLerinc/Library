package com.legend.library.controller;

import com.legend.library.model.Member;
import com.legend.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("members")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public String getAllTheMembers(Model model, String filterText) {

        List<Member> members;
        if(filterText == null || filterText.isEmpty()){
            members = memberService.findAll();
        }else {
            members = memberService.findMembersByFilterText(filterText);
        }

        model.addAttribute("members", members);
        model.addAttribute("filterText", filterText);

        return "members/list-members";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model theModel) {

        Member member = new Member();

        theModel.addAttribute("member", member);

        return "members/add-member";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("memberId") int theId,
                                    Model theModel) {

        Member member = memberService.findById(theId);

        theModel.addAttribute("member", member);

        return "members/add-member";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member member) {

        member.setActive(true);
        member.setEnrollmentDate(LocalDate.now());
        member.setBooks(new ArrayList<>());
        memberService.addMember(member);

        return "redirect:/members/list";
    }

    @GetMapping("/back")
    public String goBack() {
        return "redirect:/members/list";
    }



}
