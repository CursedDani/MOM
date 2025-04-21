package com.example.controller;

import com.example.model.Queue;
import com.example.model.User;
import com.example.service.QueueService;
import com.example.service.AuthService;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @Autowired
    private AuthService authService; // Use AuthService for authentication logic

    @Autowired
    private UserRepository userRepository; // Add UserRepository for user lookup

    @PostMapping
    public Queue createQueue(@RequestBody Queue queue, @RequestParam String connectedUsername) {
        // Get the authenticated user
        User owner = authService.getAuthenticatedUser();

        // Find the connected user
        User connectedUser = userRepository.findByUsername(connectedUsername)
                .orElseThrow(() -> new RuntimeException("Connected user not found"));

        // Set the owner and connected user
        queue.setOwner(owner);
        queue.setConnectedUser(connectedUser);

        return queueService.createQueue(queue);
    }

    @DeleteMapping("/{queueName}")
    public String deleteQueue(@PathVariable String queueName) {
        // Get the authenticated user
        User user = authService.getAuthenticatedUser();
        queueService.deleteQueue(queueName, user);
        return "Queue deleted successfully";
    }

    @PostMapping("/{queueName}/messages")
    public String pushMessage(@PathVariable String queueName, @RequestBody String messageContent) {
        // Get the authenticated user
        User sender = authService.getAuthenticatedUser();
        return queueService.pushMessage(queueName, messageContent, sender);
    }

    @GetMapping("/{queueName}/messages")
    public String pullMessage(@PathVariable String queueName) {
        // Get the authenticated user
        User receiver = authService.getAuthenticatedUser();
        return queueService.pullMessage(queueName, receiver);
    }

    @GetMapping
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }
}