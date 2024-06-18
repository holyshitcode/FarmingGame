/*
package game.farming.repository;

import game.farming.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MemoryMemberRepositoryTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void findByUsername() {
        Member member = new Member("userA");
        Member member2 = new Member("userB");
        memberRepository.save(member);
        memberRepository.save(member2);

        Member userA = memberRepository.findById(member.getId());
        Member userB = memberRepository.findById(member2.getId());
        System.out.println("userB = " + userB);
        System.out.println("userA = " + userA);
        Assertions.assertThat(userA).isNotSameAs(userB);


    }

    @Test
    void findById() {
    }

    @Test
    void save() {
        Member member = new Member();
        member.setUsername("userA");
        memberRepository.save(member);
        Member result = memberRepository.findById(member.getId());
        System.out.println("result = " + result);
        System.out.println("member = " + member);
        Assertions.assertThat(result).isEqualTo(member);
    }
}*/
