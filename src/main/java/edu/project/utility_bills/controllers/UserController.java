package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping
    public
    void createUser (@RequestBody User user) {

        userService.createUser(user);


    }


}
