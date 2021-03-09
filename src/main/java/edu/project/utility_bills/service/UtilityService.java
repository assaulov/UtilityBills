package edu.project.utility_bills.service;
/*
Сервис, который реализует основную бизнес-логику оперций над показаниями счетчиков.
 */
import edu.project.utility_bills.dao.UserRepository;
import edu.project.utility_bills.dao.UtilityRepository;
import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
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


    /*@Transactional
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

    }*/

    //Сохранения показания
    public void saveUtilities(Utilities utility) {
       User user = userRepository.findByUsername(utility.getUser().getUsername());
        LOGGER.info(String.valueOf(user.getId())+" " + user.getUsername());
        utility.setUser(user);
        LOGGER.info(utility.toString());
        utilityRepository.addUtility(utility.getUser(),
                utility.getDateOfWriteUtilityMeter(),
                utility.getColdWater(),
                utility.getHotWater(),
                utility.getElectricity(),
                utility.getGas(),
                utility.getHouseUtility(),
                utility.getCapitalRepair());
        LOGGER.info("Сервис saveUtilities");
    }

    //Найти все доступные показания
    @Transactional
    public List<UtilityResponse> findAll(UtilityRequest request) {
        List<User> users = Collections.singletonList(userRepository.findByUsername(request.getUsername()));

        if(users.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<Utilities> utilities =users.get(0).getUtilitiesList();

        return utilities.stream().map(this::getResponse).collect(Collectors.toList());
    }

    //Поиск показаний по дате
    @Transactional
    public List<UtilityResponse> findAllUtilitiesByDate(UtilityRequest request) {

        List<Utilities> utilitiesList = utilityRepository.findUtilitiesByDate(request.getDateOfWriteUtilityMeter());

        return utilitiesList.stream().map(this::getResponse).collect(Collectors.toList());
    }

    //Поиск показаний за период
    @Transactional
    public List<UtilityResponse> findUtilitiesByPeriod(UtilityRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        List<Utilities> utilitiesList = utilityRepository.findUtilitiesByPeriod(request.getDateFrom(), request.getDateTo(), user);
        return utilitiesList.stream().map(this::getResponse).collect(Collectors.toList());
    }



    //Удаление показаний по ID
    @Transactional
    public
    void deleteById(UtilityRequest request){
       utilityRepository.deleteById(request.getUtilityId());
    }


    //бновление показаний по ID
    @Transactional
    public void updateById(UtilityRequest request,Utilities utility) {
        LOGGER.info("Сервис обновления данных updateById");
        Utilities myUtility = utilityRepository.findUtilityById(request.getUtilityId());
        LOGGER.info("До: "+ String.valueOf(myUtility));
        myUtility.setColdWater(utility.getColdWater());
        myUtility.setHotWater(utility.getHotWater());
        myUtility.setElectricity( utility.getElectricity());
        myUtility.setGas(utility.getGas());
        myUtility.setHouseUtility(utility.getHouseUtility());
        myUtility.setCapitalRepair(utility.getCapitalRepair());
        LOGGER.info("После: " + String.valueOf(myUtility));
        LOGGER.info("Сервис отработал");
    }



    // Параметры счетчиков для отображения
    private UtilityResponse getResponse (Utilities ut) {

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
            ur.setUtilityId(ut.getUtilityId());

            return ur;
    }



}


