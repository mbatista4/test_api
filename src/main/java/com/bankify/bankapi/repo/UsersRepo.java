package com.bankify.bankapi.repo;

import com.bankify.bankapi.documents.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UsersRepo extends MongoRepository<Users,String> {

    @Query("{username:'?0'}")
    Users findByUsername(String username);

}
