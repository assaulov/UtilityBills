package edu.project.utility_bills.service;

import edu.project.utility_bills.dao.UserRepository;
import edu.project.utility_bills.rest.UserController;
import edu.project.utility_bills.view.UserInsert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;


@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class UserServiceTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddUser() {
        UserInsert ins = new UserInsert();
        ins.setLastName("Maus");
        ins.setFirstName("Mikki");
        ins.setMiddleName("Disneevich");
        ins.setDateOfBirth(LocalDate.of(1928, 1, 18));
        ins.setPseudoName("Mish");
        LOGGER.info("ins is created");
        userController.createUser(ins);
        ins.setLastName("last");
        ins.setFirstName("first");
        ins.setMiddleName("middle");
        ins.setDateOfBirth(LocalDate.of(2000,3,3));
        ins.setPseudoName("pseudo");
        userController.createUser(ins);
    }


}