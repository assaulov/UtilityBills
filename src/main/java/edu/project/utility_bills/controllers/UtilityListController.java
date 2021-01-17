package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/utility")
public class UtilityListController {


    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityListController.class);

    @Autowired
    private UtilityService utilityService;

    @GetMapping
    public  String showPage(Model model) {

        return "utility";
    }


    @GetMapping("/ALL")
    public String findAllUtilitiesOfAllUsers(Model model) {
        List<Utilities> utilitiesList = utilityService.findAll();
        model.addAttribute("utilities", utilitiesList);
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "utility";
    }

    @RequestMapping ("/userId")
    public String findUtilitiesByUserId(Model model, @RequestParam("userId") String userId) throws NullPointerException{
        long ID;
        LOGGER.info(userId + " получен");
        LOGGER.info("userId parse long");
        try {
             ID = Long.parseLong(userId);
        } catch (NumberFormatException e) {

            return "404";
        }

        UtilityRequest ur = new UtilityRequest();
        LOGGER.info(ur.toString());
        ur.setUserId(ID);
        LOGGER.info("ur id set");
        LOGGER.info(ur.toString());
        LOGGER.info("Try find utility by user id");
        List<UtilityResponse> responses = utilityService.findUtilitiesByUserId(ur);
        LOGGER.info("Utility added to responses");
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addAttribute("utilities", responses);
        LOGGER.info("Model add atribute");
        LOGGER.info("return utility.jsp");
        return "utility";

    }


}

