package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.UtilityCost;
import edu.project.utility_bills.service.UtilityCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Component
@Path("/cost")
public class UtilityCostController {

    @Autowired
    UtilityCostService utilityCostService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUtilityCost(UtilityCost cost) {
        utilityCostService.addUtilityCost(cost);
        System.out.println("Сделано!");
    }
}
