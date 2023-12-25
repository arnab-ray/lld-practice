package org.example.message_queue.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import org.example.message_queue.subcription.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@AllArgsConstructor
public class TopicSubscriber {
    private final AtomicInteger offSet;
    private final Subscriber subscriber;

    public TopicSubscriber(@NonNull Subscriber subscriber) {
        this.offSet = new AtomicInteger(0);
        this.subscriber = subscriber;
    }
}
