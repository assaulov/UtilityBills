package edu.project.utility_bills.controllers;


import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/utility")
public class UtilityListController {




    @Autowired
    private UtilityService utilityService;

    @Autowired
    private UtilityRequest ur;


    @GetMapping
    public  String showPage() {
        return "utility";
    }


    @GetMapping("/ALL")
    public String findAllUtilitiesOfAllUsers(Model model) {
        List<UtilityResponse> utilitiesList = utilityService.findAll();
        model.addAttribute("utilities", utilitiesList);
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "utility";
    }

    @RequestMapping ("/userId")
    public String findUtilitiesByUserId(Model model, @RequestParam("userId") String userId) throws NullPointerException{

        try {
            ur.setUserId(Long.parseLong(userId));
            List<UtilityResponse> responses = utilityService.findUtilitiesByUserId(ur);
            model.addAttribute("utilities", responses);
            model.addAttribute("userId", Long.parseLong(userId));
        } catch (NumberFormatException e) {
            String userror = "Введите id пользователя!";
            model.addAttribute("userror", userror);
        }
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "utility";

    }

    @GetMapping("/findByDate")
    public String findUtilitiesByDate(Model model, @RequestParam("findByDate") String date) {


        try {
            ur.setDateOfWriteUtilityMeter(LocalDate.parse(date));
            List<UtilityResponse> responses = utilityService.findAllUtilitiesByDate(ur);
            model.addAttribute("utilities", responses);
            model.addAttribute("date", LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException e) {
            String str = "Введите дату!";
            model.addAttribute("str", str);
        }
        return "utility";
    }

    @GetMapping("/findByPeriod")
    public  String findUtilitiesByPeriod(Model model,
                                         @RequestParam("dateFrom") String dateFrom,

                                         @RequestParam("dateTo") String dateTo) {
         try {
            ur.setDateFrom(LocalDate.parse(dateFrom));
            ur.setDateTo(LocalDate.parse(dateTo));
             List<UtilityResponse> responses = utilityService.findUtilitiesByPeriod(ur);
             model.addAttribute("utilities", responses);
             model.addAttribute("dateFrom", LocalDate.parse(dateFrom).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
             model.addAttribute("dateTo", LocalDate.parse(dateTo).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        } catch (DateTimeParseException e) {
             String str = "Введите Период!";
             model.addAttribute("str", str);
        }

        return "utility";

    }



    }


