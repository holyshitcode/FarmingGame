package game.farming.repository;

import game.farming.domain.Member;

import java.util.List;

public interface MemberRepository {
    public Member findByUsername(String username,Long id);
    public Member findById(Long id);
    public Member save(Member member);
    public List<Member> findAll();
}
