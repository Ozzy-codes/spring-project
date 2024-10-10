package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountAlreadyExistsException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    public ResponseEntity<Account> registerUser(@RequestBody Account account) {
        Account output = accountService.addUser(account);
        if(output != null) {
        return ResponseEntity.ok().body(output);
        }
        return null;
    }

    @PostMapping("login")
    public ResponseEntity<Account> authenticateUser(@RequestBody Account account) {
        Account output = accountService.login(account);
        if(output != null) return ResponseEntity.ok().body(output);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok().body(messageService.getMessages());
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createNewMessage(@RequestBody Message msg) {
        Message output = messageService.createNewMessage(msg);
        if(output != null) return ResponseEntity.ok().body(output);
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable Integer messageId) {
        return ResponseEntity.ok().body(messageService.getMessageById(messageId));
    }

    @DeleteMapping("messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId) {
        return ResponseEntity.ok().body(messageService.deleteMessageById(messageId));
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleAccountAlreadyExists(AccountAlreadyExistsException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDataIntegretyViolation(DataIntegrityViolationException ex) {
        return ex.getMessage();
    }

}
