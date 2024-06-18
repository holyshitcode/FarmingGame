package game.farming.controller;


import game.farming.domain.Member;
import game.farming.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/add")
    public String memberAddForm(Model model) {
        model.addAttribute("member", new Member());
        return "member/memberAddForm";
    }

    @PostMapping("/add")
    public String memberAdd(@Validated @ModelAttribute Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/memberAddForm";
        }
        memberService.join(member);
        log.info("member: {}", member);
        return "redirect:/";
    }
    @GetMapping("/list")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "member/memberList";
    }

    /*@PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test1");
        member.setName("Admin");
        memberService.join(member);
    }*/

}
