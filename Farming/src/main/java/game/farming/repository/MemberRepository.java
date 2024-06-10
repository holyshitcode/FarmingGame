package game.farming.repository;

import game.farming.domain.Member;

public interface MemberRepository {
    public Member findByUsername(String username,Long id);
    public Member findById(Long id);
    public Member save(Member member);
}
