package edu.project.utility_bills.controllers;


import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("utility")
public class UtilityListController {



    @Autowired
    private UtilityService utilityService;

    @Autowired
    private UtilityRequest ur;


    @GetMapping
    public ModelAndView showPage() {
        return new ModelAndView("utility");
    }


    @GetMapping("/ALL")
    public ModelAndView findAllUtilitiesOfAllUsers( ) {
        List<UtilityResponse> utilitiesList = utilityService.findAll();
        ModelAndView model = new ModelAndView();
        model.setViewName("utility");
        model.addObject("utilities", utilitiesList);
        model.addObject("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return model;
    }

    @GetMapping("/findByUserId")
    public ModelAndView findUtilitiesByUserId(ModelAndView model,  @RequestParam("userId") String userId) throws NullPointerException{
        model.setViewName("utility");
        try {
            ur.setUserId(Long.parseLong(userId));
            List<UtilityResponse> responses = utilityService.findUtilitiesByUserId(ur);
            model.addObject("utilities", responses);
            model.addObject("userId", Long.parseLong(userId));
        } catch (NumberFormatException e) {
            String userror = "Введите id пользователя!";
            model.addObject("userror", userror);
        }
        model.addObject("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return model;

    }

    @GetMapping("/findByDate")
    public ModelAndView findUtilitiesByDate(ModelAndView model, @RequestParam("findByDate") String date) {

        model.setViewName("utility");

        try {
            ur.setDateOfWriteUtilityMeter(LocalDate.parse(date));
            List<UtilityResponse> responses = utilityService.findAllUtilitiesByDate(ur);
            model.addObject("utilities", responses);
            model.addObject("date", LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException e) {
            String str = "Введите дату!";
            model.addObject("str", str);
        }
        return model;
    }

    @GetMapping("/findByPeriod")
    public  ModelAndView findUtilitiesByPeriod(ModelAndView model,
                                         @RequestParam("dateFrom") String dateFrom,

                                         @RequestParam("dateTo") String dateTo) {
        model.setViewName("utility");

        try {
            ur.setDateFrom(LocalDate.parse(dateFrom));
            ur.setDateTo(LocalDate.parse(dateTo));
             List<UtilityResponse> responses = utilityService.findUtilitiesByPeriod(ur);
             model.addObject("utilities", responses);
             model.addObject("dateFrom", LocalDate.parse(dateFrom).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
             model.addObject("dateTo", LocalDate.parse(dateTo).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException e) {
             String str = "Введите Период!";
             model.addObject("str", str);
        }

        return model;

    }



}


