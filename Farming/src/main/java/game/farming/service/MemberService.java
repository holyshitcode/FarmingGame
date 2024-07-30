package game.farming.service;

import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.repository.H2DbPlayerRepository;
import game.farming.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final H2DbPlayerRepository playerRepository;
    private final MemberRepository memberRepository;


    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long id){
        return memberRepository.findById(id);

    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }
    public Optional<Member> findByLoginId(String loginId) {
        return memberRepository.findAll().stream().filter(member -> member.getLoginId().equals(loginId)).findFirst();
    }

    public void getPlayer(Long memberId, Player player) {
        Member foundMember = memberRepository.findById(memberId);
        foundMember.setPlayer(player);
        memberRepository.save(foundMember);
    }
}
