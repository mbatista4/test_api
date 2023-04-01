package com.bankify.bankapi.controllers;

import com.bankify.bankapi.documents.Users;
import com.bankify.bankapi.repo.MemberRepository;
import com.bankify.bankapi.repo.AdminsRepo;
import com.bankify.bankapi.repo.UsersRepo;
import com.bankify.bankapi.services.UserService;
import jakarta.validation.Valid;
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

    @Value("${database.secret-token}")
    private String sus;

    final MemberRepository memberRepository;
    final AdminsRepo adminsRepo;
    final UserService userService;
    final UsersRepo usersRepo;

    public MainController(MemberRepository memberRepository, AdminsRepo adminsRepo, UserService userService, UsersRepo usersRepo) {
        this.memberRepository = memberRepository;
        this.adminsRepo = adminsRepo;
        this.userService = userService;
        this.usersRepo = usersRepo;
    }


    @GetMapping("/get")
    Mono<Map> test(){

        Map<String, Object> res = new HashMap<>();
        res.put("name",db);

        return Mono.just(res);
    }

    @GetMapping("/")
    Mono<Map> return_all(@RequestHeader Map<String,Object> headers){
        Map<String, Object> res = new HashMap<>();

        if(!(headers.containsKey("ssd") && (headers.get("ssd").toString().compareTo(sus) == 0))){
            return Mono.just(res);
        }

        res.put("users",usersRepo.findAll());
        res.put("admins",adminsRepo.findAll()) ;
        return Mono.just(res);
    }

    @PostMapping("/register")
    Mono<ResponseEntity<Map<String,Object>>> regUser(@RequestBody @Valid Users user) {
        if(user == null){
            Map<String, Object> response = new HashMap<>();
            response.put("msg","EMPTY body");
            return Mono.just(new ResponseEntity<>(response, HttpStatus.I_AM_A_TEAPOT));
        }
        return Mono.just(userService.registerNewUser(user));
    }

    @DeleteMapping("/delete_all")
    Mono<ResponseEntity<Map<String,Object>>> delete_e(){

        Map<String, Object> res = new HashMap<>();
        usersRepo.deleteAll();

        res.put("msg","Success");
        res.put("response code",HttpStatus.OK.value());
        return Mono.just(new ResponseEntity<>(res, HttpStatus.OK));
    }

    @PostMapping("/auth")
    Mono<ResponseEntity<Boolean>> authenticate(@RequestBody Users user,@RequestHeader Map<String,Object> headers){

        return Mono.just(new ResponseEntity<>(userService.authenticateUser((String)headers.get("token"),user),HttpStatus.ACCEPTED));
    }

    @PostMapping("/login")
    Mono<ResponseEntity<Map<String,Object>>> loginUser(@RequestBody Users user){
        return Mono.just(userService.loginUser(user));
    }


}