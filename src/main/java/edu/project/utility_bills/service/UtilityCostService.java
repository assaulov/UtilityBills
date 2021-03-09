package edu.project.utility_bills.service;
/*
Сервис, который реализует основную бизнес-логику оперций над тарифами.
 */

import edu.project.utility_bills.dao.UtilityCostRepository;
import edu.project.utility_bills.domain.UtilityCost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UtilityCostService {

    @Autowired
    UtilityCostRepository utilityCostRepository;

    @Transactional
    public void addUtilityCost(UtilityCost cost) {
        utilityCostRepository.addUtilityCost(
                cost.getColdWaterCost(),
                cost.getHotWaterCost(),
                cost.getElectricityCost(),
                cost.getGasCost()
        );
    }
}
