package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByPostedBy(Integer postedBy);

    //  NOTE: is there a native way to get a return of rows affected for DML operations via SpringJPA?,
    // switching the return to Integer is not working
    @Modifying
    @Query(value = "UPDATE message SET messageText = ?1 WHERE messageId = ?2", nativeQuery = true)
    void updateMessage(String msgText, Integer id);
}
