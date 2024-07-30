package game.farming.repository;

import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.service.PlayerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class H2DbPlayerRepositoryTest {


    @Autowired
    private PlayerService playerService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createPlayer() {
        Member member = new Member("new","admin","admin");
        memberRepository.save(member);

        Player newPlayer = new Player("braveMan", 10000, 5);
        playerService.save(newPlayer);

        member.setPlayer(newPlayer);

        Assertions.assertThat(member.getPlayer()).isNotNull();
    }

    @Test
    void getPlayerById() {

    }

}