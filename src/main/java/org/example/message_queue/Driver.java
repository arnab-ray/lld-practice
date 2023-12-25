package org.example.message_queue;

import org.example.message_queue.models.Message;
import org.example.message_queue.models.Topic;
import org.example.message_queue.subcription.DefaultSubscriber;

public class Driver {
    public static void main(String[] args) throws InterruptedException {
        final MessageQueue queue = new MessageQueue();
        final Topic topic1 = queue.createTopic("t1");
        final Topic topic2 = queue.createTopic("t2");
        final DefaultSubscriber sub1 = new DefaultSubscriber("sub1", 10000);
        final DefaultSubscriber sub2 = new DefaultSubscriber("sub2", 10000);
        queue.subscribe(topic1, sub1);
        queue.subscribe(topic1, sub2);

        final DefaultSubscriber sub3 = new DefaultSubscriber("sub3", 5000);
        queue.subscribe(topic2, sub3);

        queue.publish(topic1, new Message("m1"));
        queue.publish(topic1, new Message("m2"));

        queue.publish(topic2, new Message("m3"));

        Thread.sleep(15000);
        queue.publish(topic2, new Message("m4"));
        queue.publish(topic1, new Message("m5"));

        queue.resetOffSet(topic1, sub1, 0);
    }
}
