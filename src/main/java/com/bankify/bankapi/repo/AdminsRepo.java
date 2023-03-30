package com.bankify.bankapi.repo;

import com.bankify.bankapi.documents.members;
import com.bankify.bankapi.documents.admins;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface AdminsRepo extends MongoRepository<admins,String> {

    @Query("{userId:'?0'}")
    members findAdminsByUserId(String userId);
}
