package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Controller
@Path("/utility")
public class UtilityController {

    @Autowired
    UtilityService utilityService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUtility(@RequestBody Utilities utility){
        utilityService.addUtility(utility);
    }


    @POST
    @Path("/find")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UtilityResponse> findUtilities(UtilityRequest request) {
        return utilityService.findUtilities(request);
    }


}
