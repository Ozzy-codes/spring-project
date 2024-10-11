package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        if (isValidTextMessage(msg.getMessageText())) {
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

    public List<Message> getUserMessages(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
 
    @Transactional
    public Integer deleteMessageById(Integer id) {
        if(messageRepository.findById(id).isPresent()) {
        messageRepository.deleteById(id);
        return 1;
        }
        return null;
    }
    
    @Transactional
    public Integer updateMessageById(Integer id, Message msg) {
        if(!messageRepository.findById(id).isPresent() || !isValidTextMessage(msg.getMessageText())) {
        return null;
        }
        messageRepository.updateMessage(msg.getMessageText(), id);
        return 1;
    }

    private Boolean isValidTextMessage(String text) {
        if (text.length() != 0 && text.length() < 255)
            return true;
        return false;
    }
}

