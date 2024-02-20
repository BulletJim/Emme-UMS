package com.emme.services;


import com.emme.entities.ApplicationUser;
import com.emme.entities.RegistrationObject;
import com.emme.entities.Role;

import com.emme.exceptions.EmailAlreadyTakenException;
import com.emme.exceptions.RoleNotFoundInException;
import com.emme.exceptions.UserDoesNotExistException;
import com.emme.repositories.RoleRepository;
import com.emme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;


@Service
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }


    public ApplicationUser getUserByUsername(String username){

        return userRepository.findByUsername(username).orElseThrow(UserDoesNotExistException::new);

    }

    public ApplicationUser updateUser(ApplicationUser user){
        try {
            return userRepository.save(user);
        } catch(Exception e){
            throw new EmailAlreadyTakenException();
        }
    }

    private String generateUsername(String name){
        long generatedNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name + generatedNumber;
    }


    public ApplicationUser saveUser(RegistrationObject registrationObject){

        ApplicationUser user = new ApplicationUser();
        user.setFirstName(registrationObject.getFirstName());
        user.setLastName(registrationObject.getLastName());
        user.setEmail(registrationObject.getEmail());
        user.setDob(registrationObject.getDob());

        String name = user.getFirstName() + user.getLastName();

        boolean nameTaken = true;

        String tempName = "";
        while (nameTaken){
            tempName = generateUsername(name);
            if(userRepository.findByUsername(tempName).isEmpty()){
                nameTaken = false;
            }
        }
        user.setUsername(tempName);


        Set<Role> role = user.getAuthorities();
        role.add(roleRepository.findByAuthority("USER").orElseThrow(RoleNotFoundInException::new));
        user.setAuthorities(role);
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new EmailAlreadyTakenException();
        }

    }


}
