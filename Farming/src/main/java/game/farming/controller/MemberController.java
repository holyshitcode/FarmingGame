package game.farming.controller;


import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.domain.SessionConst;
import game.farming.service.MemberService;
import game.farming.service.PlayerService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PlayerService playerService;

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

    @GetMapping("/players/add")
    public String playerAddForm(Model model, HttpServletRequest request) {
        log.info("addform start");
        model.addAttribute("player", new Player());
        return "member/playerAddForm";
    }

    @PostMapping("/players/add")
    public String addPlayer(@ModelAttribute Player player, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "member/playerAddForm";
        }
        HttpSession session = request.getSession(false);
        Member attribute =(Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (attribute.getPlayer() != null) {
            throw new IllegalStateException("중복 플레이어 생성");
        }
        player.setWorkers(1);
        player.setMoney(10000);
        playerService.save(player);
        memberService.getPlayer(attribute.getId(), player);


        return "redirect:/member/players";

    }

    @GetMapping("/players")
    public String playerList(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Member member = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        model.addAttribute("player", member.getPlayer());
        return "member/playerList";
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
