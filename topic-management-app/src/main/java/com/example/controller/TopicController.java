package com.example.controller;

import com.example.model.Topic;
import com.example.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @PostMapping
    public Topic createTopic(@RequestBody Topic topic) {
        return topicService.createTopic(topic);
    }

    @DeleteMapping("/{topicId}")
    public void deleteTopic(@PathVariable Long topicId) {
        topicService.deleteTopic(topicId);
    }

    @PostMapping("/{topicId}/publish")
    public Topic publishMessage(@PathVariable Long topicId, @RequestBody String message) {
        return topicService.publishMessage(topicId, message);
    }

    @GetMapping("/{topicId}/subscribe")
    public List<String> subscribeToTopic(@PathVariable Long topicId) {
        return topicService.subscribeToTopic(topicId);
    }

    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }
}