package com.quarrel.bomber.nouveau.schedule;

import com.quarrel.bomber.nouveau.bomber.Bomber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.LinkedList;
import java.util.List;

@Configuration
@EnableScheduling
public class BombingTask {

    private static final Logger logger = LoggerFactory.getLogger(BombingTask.class);

    @Value("${app.bombers.per_tick}")
    private int perTick;
    @Value("${app.bombers.host}")
    private String host;
    @Value("${app.bombers.port}")
    private int port;

    @Scheduled(fixedDelay = 1_000)
    public void scheduleFixedDelayTask() {
        logger.info("Deploying bombers. Host: {} Port: {} Count of Bombers: {}", host, port, perTick);
        List<Thread> bombers = new LinkedList<>();

        for (int i = 0; i < perTick; i++) {
            Runnable bomber = new Bomber(host, port);
            bombers.add(new Thread(bomber));
        }

        bombers.forEach(Thread::start);
    }
}
