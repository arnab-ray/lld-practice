package org.example.message_queue.subcription;

import lombok.Getter;
import lombok.NonNull;
import org.example.message_queue.models.Topic;
import org.example.message_queue.models.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

@Getter
public class TopicHandler {
    private final Topic topic;
    private final Map<String, SubscriptionPoller> subscriptionPollerMap;

    public TopicHandler(@NonNull Topic topic) {
        this.topic = topic;
        this.subscriptionPollerMap = new HashMap<>();
    }

    public void publish() {
        for (TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            startSubscriberPoller(topic, topicSubscriber);
        }
    }

    public void startSubscriberPoller(@NonNull Topic topic, @NonNull TopicSubscriber topicSubscriber) {
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if (!subscriptionPollerMap.containsKey(subscriberId)) {
            final SubscriptionPoller subscriptionPoller = new SubscriptionPoller(topic, topicSubscriber);
            subscriptionPollerMap.put(subscriberId, subscriptionPoller);
            new Thread(subscriptionPoller).start();
        }

        final SubscriptionPoller subscriptionPoller = subscriptionPollerMap.get(subscriberId);
        subscriptionPoller.wakeUpIfNeeded();
    }
}
