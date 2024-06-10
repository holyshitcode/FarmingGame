package game.farming.service;

import game.farming.domain.Member;
import game.farming.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long id){
        return memberRepository.findById(id);

    }

    public List<Member> findAllMember(){
        return memberRepository.findAll();
    }
}
