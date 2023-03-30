package com.bankify.bankapi;

import com.bankify.bankapi.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableConfigurationProperties(ConfigProperties.class)
public class BankApiApplication {



    public static void main(String[] args) {
        SpringApplication.run(BankApiApplication.class, args);
    }

}
