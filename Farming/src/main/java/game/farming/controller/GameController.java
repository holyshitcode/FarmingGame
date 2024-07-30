package game.farming.controller;


import game.farming.domain.Member;
import game.farming.domain.SessionConst;
import game.farming.service.DelayService;
import game.farming.service.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.TimeUnit;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;


    @GetMapping("/game/farm")
    public String farm(@ModelAttribute Member member,HttpServletRequest request,Model model) {
        Member sessionMember = getSessionMember(request);
        model.addAttribute("member", sessionMember);
        log.info("game entered={}",member.getName());
        return "game/farming";
    }

    @PostMapping("/game/farm")
    public String farm(@ModelAttribute Member member, Model model, HttpServletRequest request) {
        Member member1 = getSessionMember(request);
        if (member1.getPlayer().getWorkers() <= 0) {
            return "redirect:/game/farm";
        }

        log.info("game started={}",member.getName());

        log.info("member1.getWorker={}", member1.getPlayer().getWorkers());
        gameService.startFarming(10, TimeUnit.SECONDS, member1);
        losingWorker(member1);
        return "redirect:/game/farm";

    }

    private static void losingWorker(Member member1) {
        int workers = member1.getPlayer().getWorkers();
        member1.getPlayer().setWorkers(workers-1);
    }

    private static Member getSessionMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
    }

}
