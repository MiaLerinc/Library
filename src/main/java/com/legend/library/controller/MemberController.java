package com.legend.library.controller;

import com.legend.library.model.Book;
import com.legend.library.model.Member;
import com.legend.library.service.BookService;
import com.legend.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("members")
public class MemberController {

    private MemberService memberService;
    private BookService bookService;

    @Autowired
    public MemberController(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String getAllTheMembers(Model model, String filterText) {
        if(filterText != null) {
            filterText = filterText.trim();
        }
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
        theModel.addAttribute("title", "Add Member");

        return "members/add-member";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("memberId") int theId,
                                    Model theModel) {

        Member member = memberService.findById(theId);

        theModel.addAttribute("member", member);
        theModel.addAttribute("title", "Update Member");

        return "members/add-member";
    }

    @PostMapping("/save")
    public String saveMember(@ModelAttribute("member") Member member) {

        if(member.getId() == 0) {
            member.setActive(true);
            member.setEnrollmentDate(LocalDate.now());
            member.setBooks(new ArrayList<>());
        }

        memberService.addMember(member);

        return "redirect:/members/list";
    }

    @GetMapping("/back")
    public String goBack() {
        return "redirect:/members/list";
    }

    @GetMapping("/info")
    public String showInfoModal(@RequestParam("memberId") int theId,
                                    Model theModel) {

        Member member = memberService.findById(theId);

        theModel.addAttribute("infoMember", member);

        return "members/info-member";
    }

    @GetMapping("/lending")
    public String showFormForLending(Model model) {

        return "lending/lend-book";
    }

    @GetMapping("/lendingList")
    public String getTheMembers(Model model, @RequestParam("memberFilter") String memberFilter) {
        if(memberFilter != null) {
            memberFilter = memberFilter.trim();
        }
        List<Member> members;
        if(memberFilter == null || memberFilter.isEmpty()){
            members = memberService.findAll();
        }else {
            members = memberService.findMembersByFilterText(memberFilter);
        }

        model.addAttribute("members", members);

        return "members/list-members-lending";
    }

    @PostMapping("/lendBook")
    public String lendBook(int chosenMember, int chosenBook) {

        Member member = memberService.findById(chosenMember);
        Book book = bookService.findById(chosenBook);

        book.lendBook(book, member);
        member.addBook(book);

        memberService.addMember(member);
        bookService.addBook(book);

        return "redirect:/members/lending";
    }

    @GetMapping("/returning")
    public String showFormForReturning(Model model) {

        return "lending/return-book";
    }

}
