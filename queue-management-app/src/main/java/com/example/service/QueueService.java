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
            message.setId(queue.getMessages().size() + 1); // Simple ID assignment
            queue.addMessage(message);
            return "Message added to queue";
        }
        return "Queue not found";
    }

    public String pullMessage(String queueName) {
        Queue queue = queues.get(queueName);
        if (queue != null && !queue.getMessages().isEmpty()) {
            // Retrieve and remove the first message from the queue
            Message message = queue.getMessages().remove(0);
            return message.getContent(); // Return the content of the message
        }
        return "No messages available in the queue";
    }

    public List<Queue> getAllQueues() {
        return new ArrayList<>(queues.values());
    }
}