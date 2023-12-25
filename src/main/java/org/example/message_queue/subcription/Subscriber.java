package org.example.message_queue.subcription;

import org.example.message_queue.models.Message;

public interface Subscriber {
    String getId();

    void consume(Message message) throws InterruptedException;
}
