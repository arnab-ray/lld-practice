package org.example.message_queue;

import lombok.NonNull;
import org.example.message_queue.models.Message;
import org.example.message_queue.models.Topic;
import org.example.message_queue.models.TopicSubscriber;
import org.example.message_queue.subcription.Subscriber;
import org.example.message_queue.subcription.TopicHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MessageQueue {
    private final Map<String, TopicHandler> topicHandlers;

    public MessageQueue() {
        this.topicHandlers = new HashMap<>();
    }

    public Topic createTopic(@NonNull String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        System.out.println("Created topic: " + topic.getTopicName());
        return topic;
    }

    public void subscribe(@NonNull Topic topic, @NonNull Subscriber subscriber) {
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " subscribed to topic: " + topic.getTopicName());
    }

    public void publish(@NonNull Topic topic, @NonNull Message message) {
        topic.addMessage(message);
        System.out.println(message.getMessage() + " published to topic: " + topic.getTopicName());
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    public void resetOffSet(@NonNull Topic topic, @NonNull Subscriber subscriber, @NonNull int newOffSet) {
        for (TopicSubscriber topicSubscriber : topic.getTopicSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffSet().set(newOffSet);
                System.out.println(topicSubscriber.getSubscriber().getId() + " offset reset to: " + newOffSet);
                new Thread(() -> topicHandlers.get(topic.getTopicId()).startSubscriberPoller(topic, topicSubscriber)).start();
                break;
            }
        }
    }
}
