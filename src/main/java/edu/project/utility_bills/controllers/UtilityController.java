package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/utility")
public class UtilityController {

    @Autowired
    UtilityService utilityService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUtility(Utilities utility){
        utilityService.addUtility(utility);
    }
}
