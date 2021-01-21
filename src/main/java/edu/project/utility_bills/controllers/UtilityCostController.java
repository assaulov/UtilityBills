package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.UtilityCost;
import edu.project.utility_bills.service.UtilityCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UtilityCostController {

    @Autowired
    UtilityCostService utilityCostService;


    public void addUtilityCost(UtilityCost cost) {
        utilityCostService.addUtilityCost(cost);
        System.out.println("Сделано!");
    }
}
