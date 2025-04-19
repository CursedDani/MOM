package com.example.service;

import com.example.model.Queue;
import com.example.model.Message;
import com.example.repository.QueueRepository;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueueService {

    @Autowired
    private QueueRepository queueRepository;

    private final Map<String, Queue> queues = new HashMap<>();

    public Queue createQueue(Queue queue, User owner) {
        // Check if a queue with the same name already exists
        if (queueRepository.findByName(queue.getName()).isPresent()) {
            throw new RuntimeException("Queue with the same name already exists");
        }

        queue.setOwner(owner);
        return queueRepository.save(queue);
    }

    public void deleteQueue(String queueName, User owner) {
        Queue queue = queueRepository.findByName(queueName)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        if (!queue.getOwner().equals(owner)) {
            throw new RuntimeException("You are not authorized to delete this queue");
        }

        queueRepository.delete(queue);
    }

    public String pushMessage(String queueName, String messageContent) {
        Queue queue = queues.get(queueName);
        if (queue != null) {
            // Create a new Message object and add it to the queue
            Message message = new Message();
            message.setContent(messageContent);
            message.setId(queue.getMessages().size() + 1); // Simple ID assignment
            message.setQueueName(queueName); // Set the queue name in the message
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