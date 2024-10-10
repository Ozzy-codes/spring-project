package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    //  NOTE: is there a native way to get a return of rows affected for DML operations via SpringJPA?

    // @Modifying
    // @Query(value = "DELETE FROM message m WHERE m.messageId = ?1", nativeQuery = true)
    // int deleteMessage(int id);
}
