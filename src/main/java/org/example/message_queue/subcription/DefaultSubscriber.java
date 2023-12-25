package org.example.message_queue.subcription;

import lombok.AllArgsConstructor;
import org.example.message_queue.models.Message;

@AllArgsConstructor
public class DefaultSubscriber implements Subscriber {
    private final String id;
    private final int sleepTimeInMillis;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber: " + id + " started consuming: " + message.getMessage());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber: " + id + " done consuming: " + message.getMessage());
    }
}
