package edu.project.utility_bills.service;

import edu.project.utility_bills.dao.UtilityRepository;
import edu.project.utility_bills.domain.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilityService {

    @Autowired
    UtilityRepository utilityRepository;

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
                utility.getCapitalRepair());

    }
}
