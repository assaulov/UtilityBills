package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component

public class UserController {


    @Autowired
    UserService userService;


    public void createUser (User user) {

        userService.createUser(user);


    }


}
