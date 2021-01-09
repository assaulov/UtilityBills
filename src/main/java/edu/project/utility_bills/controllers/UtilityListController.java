package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping(path = "/utilitiesList")
public class UtilityListController {

  /*  @Autowired
    private UtilityService utilityService;

    @GetMapping
    public  String findAllUtilities(Model model) {
        List<Utilities> list = utilityService.findAll();
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addAttribute("utilities", list);

        return "utilitiesList";
    }*/





    }

