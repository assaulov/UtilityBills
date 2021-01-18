package edu.project.utility_bills.service;

import edu.project.utility_bills.dao.UserRepository;
import edu.project.utility_bills.dao.UtilityRepository;
import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.view.LocalDateAdapter;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilityService {
private static final Logger LOGGER = LoggerFactory.getLogger(UtilityService.class);

    @Autowired
    UtilityRepository utilityRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void  addUtility(Utilities utility) {
        utilityRepository.addUtility(
                utility.getUser(),
                utility.getDateOfWriteUtilityMeter(),
                utility.getColdWater(),
                utility.getHotWater(),
                utility.getElectricity(),
                utility.getGas(),
                utility.getHouseUtility(),
                utility.getCapitalRepair()
        );

    }


    @Transactional
    public List<UtilityResponse> findUtilitiesByUserId(UtilityRequest request) {
       List<User> users = userRepository.findUserById(
                        request.getUserId());
       if(users.isEmpty()) {
                    return Collections.emptyList();
                }
        List<Utilities> utilities =users.get(0).getUtilitiesList();
       List<UtilityResponse> results =utilities.stream().map(u -> getResponse(u)).collect(Collectors.toList());
        return results;
    }

    @Transactional
    public List<UtilityResponse> findAll() {
        List<Utilities> utilitiesList = utilityRepository.findAll();
        return utilitiesList.stream().map(this::getResponse).collect(Collectors.toList());
    }


    @Transactional
    public List<UtilityResponse> findAllUtilitiesByDate(UtilityRequest request) {
        LOGGER.info("Request of utilities to repository");
        List<Utilities> utilitiesList = utilityRepository.findUtilitiesByDate(request.getDateOfWriteUtilityMeter());
        LOGGER.info("Return utilitiesList");
        return utilitiesList.stream().map(this::getResponse).collect(Collectors.toList());
    }



    private UtilityResponse getResponse (Utilities ut) {
            LOGGER.info("Response of utilities");
            UtilityResponse ur = new UtilityResponse();
            ur.setDateOfWriteUtilityMeter(
                    ut.getDateOfWriteUtilityMeter().
                            format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            ur.setColdWater(ut.getColdWater());
            ur.setHotWater(ut.getHotWater());
            ur.setElectricity(ut.getElectricity());
            ur.setGas(ut.getGas());
            ur.setHouseUtility(ut.getHouseUtility());
            ur.setCapitalRepair(ut.getCapitalRepair());

            return ur;
    }


}


