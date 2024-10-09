package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message createNewMessage(Message msg) {
        if (msg.getMessageText().length() != 0 && msg.getMessageText().length() < 255) {
            return messageRepository.save(msg);
        }
        return null;
    }

    public List<Message> getMessage() {
        return (List<Message>) messageRepository.findAll();
    }
}

