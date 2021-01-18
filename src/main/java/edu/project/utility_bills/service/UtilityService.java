package edu.project.utility_bills.service;

import edu.project.utility_bills.dao.UserRepository;
import edu.project.utility_bills.dao.UtilityRepository;
import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.view.LocalDateAdapter;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilityService {

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
    public List<Utilities> findAll() {
        return  utilityRepository.findAll();
    }


    @Transactional
    public List<UtilityResponse> findAllUtilitiesByDate(UtilityRequest request) {
        List<Utilities> utilitiesList = utilityRepository.findUtilitiesByDate(request.getDateOfWriteUtilityMeter());
        return utilitiesList.stream().map(this::getResponse).collect(Collectors.toList());
    }



    private UtilityResponse getResponse (Utilities ut) {
            UtilityResponse ur = new UtilityResponse();
            ur.setDateOfWriteUtilityMeter(ut.getDateOfWriteUtilityMeter());
            ur.setColdWater(ut.getColdWater());
            ur.setHotWater(ut.getHotWater());
            ur.setElectricity(ut.getElectricity());
            ur.setGas(ut.getGas());
            ur.setHouseUtility(ut.getHouseUtility());
            ur.setCapitalRepair(ut.getCapitalRepair());

            return ur;
    }


}


