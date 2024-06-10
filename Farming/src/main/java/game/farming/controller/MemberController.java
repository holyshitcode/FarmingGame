package game.farming.controller;


import game.farming.domain.Member;
import game.farming.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String memberAddForm() {
        return "member/memberAddForm";
    }

    @PostMapping("/add")
    public String memberAdd(@ModelAttribute Member member) {
        Member member1 = new Member();
        member1.setUsername(member.getUsername());
        member1.setMoney(4000L);
        memberService.join(member1);
        log.info("member: {}", member1);
        return "redirect:/member/list";
    }
    @GetMapping("/list")
    public String memberList(Model model) {
        List<Member> members = memberService.findAllMember();
        model.addAttribute("members", members);
        return "member/memberList";
    }
}
