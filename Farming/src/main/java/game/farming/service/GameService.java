package game.farming.service;

import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class GameService {

    private final MemberRepository memberRepository;
    private final DelayService delayService;

    public CompletableFuture<String> startFarming(long delay, TimeUnit unit, Member member) {
        if (delayService.isWorkerAlive(member.getId())) {

            return delayService.delayTask(delay, unit, () -> harvest(member));
        }else {
            throw new IllegalStateException("워커가 부족합니다");
        }
    }

    private void harvest(Member member) {
        // 수확 로직을 여기에 구현합니다.
        Player player = member.getPlayer();
        getWorker(player);
        player.setMoney(player.getMoney() + 10000); // 예: 1000의 돈을 추가
        log.info(player.getPlayerName()+":수확완료");
    }

    private static void getWorker(Player player) {
        player.setWorkers(player.getWorkers()+1);
    }
}



