package com.example.service;

import java.util.List;
import java.util.Optional;

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

    public List<Message> getMessages() {
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(Integer id) {
        Optional<Message> output = messageRepository.findById(id);
        if(output.isPresent()) return output.get();
        return null;
    }
}

