package com.challenge.magalu.config;

import com.challenge.magalu.entity.Channel;
import com.challenge.magalu.entity.Status;
import com.challenge.magalu.repository.ChannelRepository;
import com.challenge.magalu.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.logging.Logger;

@Configuration
@RequiredArgsConstructor
public class PopulateEntity implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;
    private final Logger log = Logger.getLogger(PopulateEntity.class.getName());

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);
        log.info("Channels saved");

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
        log.info("Status saved");
    }
}
