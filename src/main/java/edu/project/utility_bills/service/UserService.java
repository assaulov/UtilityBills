package edu.project.utility_bills.service;



import edu.project.utility_bills.dao.UserRepository;

import edu.project.utility_bills.domain.User;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createUser(User user) {
        LOGGER.info("Сервис");
     userRepository.createUser(
             user.getLastName(),
             user.getFirstName(),
             user.getMiddleName(),
             user.getDateOfBirth(),
             user.getPseudoName());
     LOGGER.info("Пользователь доваблен в базу!");


    }




}



