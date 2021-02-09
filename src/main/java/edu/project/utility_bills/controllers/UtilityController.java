package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.User;
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
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("utility")
public class UtilityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityController.class);


    @Autowired
    private UtilityService utilityService;

    @Autowired
    private UtilityRequest ur;



    @GetMapping("/{name}")
    public String findAll(@PathVariable String name, Model model) {
        LOGGER.info("/ALL Get mapping, метод контроддера : showPage( )");
        ur.setUsername(name);
        List<UtilityResponse> responses = utilityService.findAll(ur);
        model.addAttribute("utility");
        model.addAttribute("utilities", responses);
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "utility";
    }


    @GetMapping("{name}/findByDate")
    public String findUtilitiesByDate(Model model, @RequestParam("findByDate") String date, @PathVariable String name) {
        LOGGER.info("/findByDate Get mapping, метод контроддера : findUtilitiesByDate( )");
        model.addAttribute("utility");

        try {
            ur.setDateOfWriteUtilityMeter(LocalDate.parse(date));
            List<UtilityResponse> responses = utilityService.findAllUtilitiesByDate(ur);
            model.addAttribute("utilities", responses);
            model.addAttribute("date", LocalDate.parse(date).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            if(responses.isEmpty()){
               findAll(name, model);
            }

        } catch (DateTimeParseException e) {
            String str = "Введите дату!";
            model.addAttribute("str", str);
        }
        return "utility";
    }

    @GetMapping("{name}/findByPeriod")
    public  String findUtilitiesByPeriod(Model model,
                                               @RequestParam("dateFrom") String dateFrom,
                                               @RequestParam("dateTo") String dateTo, @PathVariable String name) {
        LOGGER.info("/findByPeriod Get mapping, метод контроддера : findUtilitiesByPeriod( )");
        model.addAttribute("utility");

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

    @PostMapping("{name}/delete")
    public  String deleteById(@RequestParam("utilityId") String utilityId, @PathVariable String name) {
        LOGGER.info("/delete Post Mapping, метод контроддера : deleteById( )");
        ur.setUtilityId(Long.parseLong(utilityId));
        utilityService.deleteById(ur);


        return "redirect:/utility/" + name;
    }


    @PostMapping("{name}/save")
    public String saveUtility(Utilities utility, @PathVariable String name,  User user) {
        LOGGER.info("/save Post Mapping, метод контроддера : saveUtility( )");
        user.setUsername(name);
        utility.setUser(user);
              try{
                  LOGGER.info(" try  utilityService.save(utility)");

                  ur.setUsername(name);
                  utilityService.saveUtilities(utility);

                } catch (Exception e) {

                  LOGGER.info(e.toString());

        }
              return "redirect:/utility/" + name;
    }



    @PutMapping(value = "{name}/update" )
    public String updateUtility(Utilities utility, @PathVariable String name) {
        LOGGER.info("/update Put Mapping, метод контроддера : updateUtility( )");
        LOGGER.info("Контроллер начинает работу");

        ur.setUsername(name);
        ur.setUtilityId(utility.getUtilityId());
            LOGGER.info(String.valueOf(utility.getUtilityId())+ " ID счетчиков");
            LOGGER.info(ur.toString() + "Запрос контроллера");
            LOGGER.info("Запрос ушел в сервис ");
            utilityService.updateById(ur,utility);
            LOGGER.info("Вернулся ответ");


         return "redirect:/utility/" + name;

    }


}


