package com.emme.controllers;


import com.emme.exceptions.*;
import com.emme.entities.ApplicationUser;
import com.emme.entities.RegistrationObject;
import com.emme.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;


@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }


    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<String>handleEmailAlreadyTakenException(){
        return new ResponseEntity<String>("the email you provided is already in use", HttpStatus.CONFLICT);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ApplicationUser createUser(@RequestBody RegistrationObject registrationObject){

        return userService.saveUser(registrationObject);
    }

    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<String>handleUserDoesNotExistException(){
        return new ResponseEntity<String>("the user does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/phone")
    public ApplicationUser updatePhoneNumber(@RequestBody LinkedHashMap<String, String> body){
        String username = body.get("username");
        String phone = body.get("phone");

        ApplicationUser user = userService.getUserByUsername(username);

        user.setPhone(phone);
        return userService.updateUser(user);
    }

}
