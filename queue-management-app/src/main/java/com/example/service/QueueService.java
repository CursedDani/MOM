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

    public Queue createQueue(Queue queue) {
        // Check if a queue with the same name already exists
        if (queueRepository.findByName(queue.getName()).isPresent()) {
            throw new RuntimeException("Queue with the same name already exists");
        }

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

    public String pushMessage(String queueName, String messageContent, User sender) {
        Queue queue = queueRepository.findByName(queueName)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        // Only the owner can send messages
        if (!queue.getOwner().equals(sender)) {
            throw new RuntimeException("You are not authorized to send messages to this queue");
        }

        // Create a new Message object and add it to the queue
        Message message = new Message();
        message.setContent(messageContent);
        message.setQueueName(queueName);
        queue.addMessage(message);

        queueRepository.save(queue);
        return "Message added to queue";
    }

    public String pullMessage(String queueName, User receiver) {
        Queue queue = queueRepository.findByName(queueName)
                .orElseThrow(() -> new RuntimeException("Queue not found"));

        // Only the connected user can pull messages
        if (!queue.getConnectedUser().equals(receiver)) {
            throw new RuntimeException("You are not authorized to pull messages from this queue");
        }

        // Retrieve and remove the first message from the queue
        Message message = queue.pullMessage();
        if (message == null) {
            return "No messages available in the queue";
        }

        queueRepository.save(queue);
        return message.getContent();
    }

    public List<Queue> getAllQueues() {
        return new ArrayList<>(queueRepository.findAll());
    }
}