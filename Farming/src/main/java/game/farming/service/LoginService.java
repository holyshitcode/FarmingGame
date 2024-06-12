package game.farming.service;


import game.farming.domain.Member;
import game.farming.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public Member login(String loginId, String password){
        return memberService.findByLoginId(loginId).filter(m->m.getPassword().equals(password)).orElse(null);
    }
}
