package com.example.controller;

import com.example.model.Queue;
import com.example.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping
    public Queue createQueue(@RequestBody Queue queue) {
        return queueService.createQueue(queue);
    }

    @PostMapping("/{queueName}/messages")
    public String pushMessage(@PathVariable String queueName, @RequestBody String message) {
        return queueService.pushMessage(queueName, message);
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