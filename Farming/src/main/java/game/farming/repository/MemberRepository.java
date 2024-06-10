package game.farming.repository;

import game.farming.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository {
    public Member findByUsername(String username,Long id);
    public Member findById(Long id);
    public Member save(Member member);
    public List<Member> findAll();
}
