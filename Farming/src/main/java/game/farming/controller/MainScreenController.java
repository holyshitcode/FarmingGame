package game.farming.controller;

import game.farming.domain.Member;
import game.farming.domain.SessionConst;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MainScreenController {

    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
        //세션에 데이터가없으면 홈으로
        if(loginMember==null){
            return "home";
        }
        //
        model.addAttribute("member", loginMember);
        return "loginHome";

    }
    @GetMapping("/main")
    public String MainModel(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,Model model){
        model.addAttribute("member", loginMember);

        return "index";
    }

}
