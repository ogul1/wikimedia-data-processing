package com.ogul.kafkaproducer;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.url}")
    private String url;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws StreamException, InterruptedException {

        BackgroundEventHandler backgroundEventHandler = new WikimediaChangesHandler(kafkaTemplate, topicName);

        EventSource.Builder builder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder backgroundBuilder = new BackgroundEventSource.Builder(backgroundEventHandler, builder);

        BackgroundEventSource eventSource = backgroundBuilder.build();
        eventSource.start();

        TimeUnit.MINUTES.sleep(10);
    }
}
