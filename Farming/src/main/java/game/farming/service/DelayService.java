package game.farming.service;


import game.farming.domain.Member;
import game.farming.domain.Player;
import game.farming.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class DelayService {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final MemberRepository memberRepository;

    public boolean isWorkerAlive(Long memberId) {
        Member foundMember = memberRepository.findById(memberId);
        int workerNum = foundMember.getPlayer().getWorkers();
        return workerNum > 0;
    }

    public CompletableFuture<String> delayTask(long delay, TimeUnit unit, Runnable task) {

        CompletableFuture<String> future = new CompletableFuture<>();
        scheduler.schedule(() -> {
            task.run();
            future.complete("Task completed");
        }, delay, unit);
        return future;
    }
}
