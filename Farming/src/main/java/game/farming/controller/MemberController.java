package game.farming.controller;


import game.farming.domain.Member;
import game.farming.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
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
@AllArgsConstructor
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
        return "redirect:/member/list";
    }
    @GetMapping("/list")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "member/memberList";
    }
    @PostConstruct
    public void init() {
        Member member = new Member("admin");
        Member member1 = new Member("Jiheon");
        Member member2 = new Member("Jun e");
        Member member3 = new Member("Dong pil");
        member.setMoney(1000000000L);
        member1.setMoney(10000L);
        member2.setMoney(10000L);
        member3.setMoney(10000L);
        memberService.join(member);
        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);

    }
}
