package com.bankify.bankapi.repo;

import com.bankify.bankapi.documents.members;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MemberRepository extends MongoRepository<members,String> {

    @Query("{userId:'?0'}")
    members findMemberByuserId(String userId);

}
