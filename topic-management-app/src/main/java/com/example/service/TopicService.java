package com.example.service;

import com.example.model.Topic;
import com.example.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public void deleteTopic(Long topicId) {
        topicRepository.deleteById(topicId);
    }

    public Topic publishMessage(Long topicId, String message) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }
        Topic topic = optionalTopic.get();
        topic.getMessages().add(message);
        return topicRepository.save(topic);
    }

    public List<String> subscribeToTopic(Long topicId) {
        Optional<Topic> optionalTopic = topicRepository.findById(topicId);
        if (optionalTopic.isEmpty()) {
            throw new RuntimeException("Topic not found");
        }
        return optionalTopic.get().getMessages();
    }

    public Optional<Topic> getTopic(Long topicId) {
        return topicRepository.findById(topicId);
    }

    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }
}