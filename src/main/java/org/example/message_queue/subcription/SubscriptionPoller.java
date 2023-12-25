package org.example.message_queue.subcription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.example.message_queue.models.Message;
import org.example.message_queue.models.Topic;
import org.example.message_queue.models.TopicSubscriber;

@Getter
@AllArgsConstructor
public class SubscriptionPoller implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    @SneakyThrows
    @Override
    public void run() {
        synchronized (topicSubscriber) {
            while (true) {
                int currOffset = topicSubscriber.getOffSet().get();
                while (currOffset >= topic.getMessages().size()) {
                    topicSubscriber.wait();
                }
                Message message = topic.getMessages().get(currOffset);
                topicSubscriber.getSubscriber().consume(message);
                topicSubscriber.getOffSet().compareAndSet(currOffset, currOffset + 1);
            }
        }
    }

    public synchronized void wakeUpIfNeeded() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
