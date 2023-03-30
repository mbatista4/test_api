package com.bankify.bankapi.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class admins {

    @Id
    private String _id;

    private String firstName;
    private String lastName;
    private String address;
    private String userId;
    private String password;
    private Boolean isClockedIn;
}
