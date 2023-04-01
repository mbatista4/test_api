package com.bankify.bankapi.documents;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
@Data
public class Users {
    @Id
    private String _id;
    @NotNull(message = "Username is required.")
    @NotBlank(message = "Username cannot be empty.")
    private String username;
    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password cannot be empty.")
    @Pattern(regexp = "^.{6,}$", message = "Minimum password Length is 6.")
    private String password;

    @Override
    public String toString(){
        Map<String, String> map = new HashMap<>();
        map.put("username",this.username);
        map.put("password",this.password);

        return map.toString();
    }
}
