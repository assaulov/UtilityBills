package edu.project.utility_bills.service;

import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.controllers.UserController;
import edu.project.utility_bills.controllers.UtilityController;
import junit.framework.TestCase;
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
public class UtilityServiceTest extends TestCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityServiceTest.class);

    @Autowired
    UtilityController utilityController;

    @Autowired
    UserController userController;

    @Test
    public void testAddUtility() {
        User user = new User();
        user.setUserId(25L);


        Utilities utils = new Utilities();
        utils.setUser(user);
        utils.setDateOfWriteUtilityMeter(LocalDate.of(2021,1, 6));
        utils.setColdWater(545);
        utils.setHotWater(0);
        utils.setElectricity(198785);
        utils.setGas(151);
        utils.setHouseUtility(236);
        utils.setCapitalRepair(2636);
        utilityController.addUtility(utils);
    }
}