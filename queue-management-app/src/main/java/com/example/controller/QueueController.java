package com.example.controller;

import com.example.model.Queue;
import com.example.model.User;
import com.example.service.QueueService;
import com.example.service.AuthService;
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

    @PostMapping
    public Queue createQueue(@RequestBody Queue queue) {
        // Get the authenticated user
        User user = authService.getAuthenticatedUser();
        return queueService.createQueue(queue, user);
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
        return queueService.pushMessage(queueName, messageContent);
    }

    @GetMapping("/{queueName}/messages")
    public String pullMessage(@PathVariable String queueName) {
        return queueService.pullMessage(queueName);
    }

    @GetMapping
    public List<Queue> getAllQueues() {
        return queueService.getAllQueues();
    }
}