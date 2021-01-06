package edu.project.utility_bills.service;



import edu.project.utility_bills.dao.UserRepository;

import edu.project.utility_bills.rest.UserController;
import edu.project.utility_bills.view.UserInsert;


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
    public void createUser(UserInsert insert) {
        LOGGER.info("Сервис");
     userRepository.createUser(
                insert.getLastName(),
                insert.getFirstName(),
                insert.getMiddleName(),
                insert.getDateOfBirth(),
                insert.getPseudoName());
     LOGGER.info("Пользователь доваблен в базу!");


    }


}



//"Assaulov", "Ivan", "Urevich", LocalDate.of(1994, 4,15), "Assa");