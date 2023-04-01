package com.bankify.bankapi.services;

import com.bankify.bankapi.documents.Users;
import com.bankify.bankapi.repo.UsersRepo;
import com.bankify.bankapi.tools.PasswordUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    final UsersRepo usersRepo;
    final PasswordUtil passwordUtil;
    final JWTService jwtService;


    public UserService(UsersRepo usersRepo, JWTService jwtService) {
        this.usersRepo = usersRepo;
        this.jwtService = jwtService;
        this.passwordUtil = new PasswordUtil();
    }

    public ResponseEntity<Map<String,Object>> registerNewUser(Users user){
        Map<String, Object> map = new HashMap<>();
        if(usersRepo.findByUsername(user.getUsername().toLowerCase()) != null){
            map.put("msg","username already exists");
            return new ResponseEntity<>(map, HttpStatus.CONFLICT);
        }

        map.put("msg","SUCCESS");
        user.setPassword(passwordUtil.encodePassword(user.getPassword()));
        map.put("usr with encoded password",user);

        usersRepo.save(user);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    public ResponseEntity<Map<String,Object>> loginUser(Users user){
        Map<String, Object> map = new HashMap<>();

        Users userFound = usersRepo.findByUsername(user.getUsername().toLowerCase());

        if(userFound==null){
            map.put("msg","username might be wrong");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        if(!passwordUtil.comparePasswords(userFound.getPassword(),user.getPassword())){
            map.put("msg","password might be wrong");
            return new ResponseEntity<>(map,HttpStatus.BAD_REQUEST);
        }
        map.put("token",jwtService.createJWT(user.getUsername().toLowerCase()));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    public boolean authenticateUser(String token, Users user){

        return jwtService.verifyJwt(token,user.getUsername());

    }
}
