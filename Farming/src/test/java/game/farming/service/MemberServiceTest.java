package game.farming.service;

import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.repository.H2DbPlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    PlayerService playerService;
    @Autowired
    H2DbPlayerRepository playerRepository;


    @Test
    void join() {
        Member member = new Member("tester","test11","test2");
        memberService.join(member);
        Optional<Member> foundMember = memberService.findByLoginId("test11");
        assertThat(foundMember).isEqualTo(Optional.of(member));
    }

    @Test
    void findMember() {
        Member member = new Member("tester","test11","test2");
        memberService.join(member);
        Member foundMember = memberService.findMember(member.getId());
        Assertions.assertThat(foundMember).isEqualTo(member);

    }

    @Test
    void findAllMember() {
        Member member = new Member("tester","test11","test2");
        Member member2 = new Member("testers","test111","test22");
        Member member3 = new Member("testeree","test1112","test222");
        memberService.join(member);
        memberService.join(member2);
        memberService.join(member3);
        List<Member> members = memberService.findAllMember();
        assertThat(members).hasSize(3);

    }

    @Test
    void findByLoginId() {
        Member member = new Member("tester","test11","test2");
        memberService.join(member);
        Optional<Member> foundMember = memberService.findByLoginId("test11");
        assertThat(foundMember).isEqualTo(Optional.of(member));

    }

    @Test
    void createPlayer() {
        Member member = new Member("tester","test11","test2");
        memberService.join(member);
        Player player = new Player("newPlayer", 100000, 5);
        Player anotherPlayer = new Player("newPlaye222r", 1001100, 15);

        log.info("player={}", player);

//        playerService.save(player);
        Player savedOne = playerRepository.save(player);
        playerService.save(anotherPlayer);
        log.info("savedOne={}", savedOne);

        memberService.getPlayer(member.getId(),player);
        log.info("newMember ={}",member.getPlayer());
        Assertions.assertThat(savedOne).isEqualTo(member.getPlayer());
        Assertions.assertThat(member.getPlayer()).isNotEqualTo(anotherPlayer);
        log.info("result savedPlayer={}, anotherSavedPlayer={}",savedOne,anotherPlayer);
    }
}