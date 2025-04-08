package com.example.service;

import com.example.model.Queue;
import com.example.model.Message;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueueService {

    private final Map<String, Queue> queues = new HashMap<>();

    public Queue createQueue(Queue queue) {
        Queue existingQueue = queues.get(queue.getName());
        if (existingQueue != null) {
            return existingQueue; // Return existing queue if it already exists
        }else{
        queues.put(queue.getName(), queue);
        return queue;
        }
    }

    public String pushMessage(String queueid, String messageContent) {
        Queue queue = queues.get(queueid);
        if (queue != null) {
            // Create a new Message object and add it to the queue
            Message message = new Message();
            message.setContent(messageContent);
            message.setQueue(queue);
            queue.addMessage(message);
            return "Message added to queue";
        }
        return "Queue not found";
    }

    public List<String> pullMessages(String queueName) {
        Queue queue = queues.get(queueName);
        if (queue != null) {
            // Convert List<Message> to List<String> (message content)
            List<String> messageContents = new ArrayList<>();
            for (Message message : queue.getMessages()) {
                messageContents.add(message.getContent());
            }
            return messageContents;
        }
        return Collections.emptyList();
    }

    public List<Queue> getAllQueues() {
        return new ArrayList<>(queues.values());
    }
}