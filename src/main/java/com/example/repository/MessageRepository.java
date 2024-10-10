package com.example.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    //  NOTE: is there a native way to get a return of rows affected for DML operations via SpringJPA?

    // @Modifying
    // @Query(value = "DELETE FROM message m WHERE m.messageId = ?1", nativeQuery = true)
    // int deleteMessage(int id);

    @Modifying
    @Query(value = "UPDATE message SET messageText = ?1 WHERE messageId = ?2", nativeQuery = true)
    void updateMessage(String msgText, Integer id);
}
