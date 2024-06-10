package game.farming.repository;

import game.farming.domain.Member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> members = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member findByUsername(String username,Long id) {
        return members.get(username);
    }

    @Override
    public Member findById(Long id) {
        return members.get(id);

    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        members.put(member.getId(),member);
        return member;
    }
}
