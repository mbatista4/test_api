package com.bankify.bankapi.controllers;

import com.bankify.bankapi.repo.MemberRepository;
import com.bankify.bankapi.repo.AdminsRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController()
public class MainController {

    @Value("${database.db-name}")
    private String db;

    final MemberRepository memberRepository;
    final AdminsRepo adminsRepo;

    public MainController(MemberRepository memberRepository, AdminsRepo adminsRepo) {
        this.memberRepository = memberRepository;
        this.adminsRepo = adminsRepo;
    }


    @GetMapping("/get")
    Mono<Map> test(){

        Map<String, Object> res = new HashMap<>();
        res.put("name",db);

        return Mono.just(res);
    }

    @GetMapping("/")
    Mono<Map> return_all(){

        Map<String, Object> res = new HashMap<>();
        res.put("name","Hello World!");
        res.put("all",memberRepository.findAll());
        res.put("admins",adminsRepo.findAll()) ;
        return Mono.just(res);
    }

    @DeleteMapping("/delete_all")
    Mono<ResponseEntity<Map>> delete_e(){

        Map<String, Object> res = new HashMap<>();
        if(memberRepository.count() ==0){
            res.put("msg","There is no Content In the Members Repo to access");
            res.put("response code",HttpStatus.I_AM_A_TEAPOT.value());
            return Mono.just(new ResponseEntity<>(res, HttpStatus.I_AM_A_TEAPOT));
        } else {
            memberRepository.deleteAll();
        }

        res.put("msg","Success");
        res.put("response code",HttpStatus.OK.value());
        return Mono.just(new ResponseEntity<>(res, HttpStatus.OK));
    }
}