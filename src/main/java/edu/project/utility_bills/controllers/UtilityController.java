package edu.project.utility_bills.controllers;
/*
Контроллер страницы отображения, добавления, редактирования и удаления показательей счетчиков.
 */

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


    //Отображает все показания счетчиков конкретного пользователя с именем {name}
    @GetMapping("/{name}")
    public String findAll(@PathVariable String name, Model model) {
        LOGGER.info("/ALL Get mapping, метод контроллера : findALL( )");
        ur.setUsername(name);
        List<UtilityResponse> responses = utilityService.findAll(ur);
        model.addAttribute("utility");
        model.addAttribute("utilities", responses);
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return "utility";
    }

    //Отображает показания счетчиков за конкретную дату
    @GetMapping("{name}/findByDate")
    public String findUtilitiesByDate(Model model, @RequestParam("findByDate") String date, @PathVariable String name) {
        LOGGER.info("/findByDate Get mapping, метод контроллера : findUtilitiesByDate( )");
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

    //Отображает показания счетчиков за конкретный период
    @GetMapping("{name}/findByPeriod")
    public  String findUtilitiesByPeriod(Model model,
                                               @RequestParam("dateFrom") String dateFrom,
                                               @RequestParam("dateTo") String dateTo, @PathVariable String name) {
        LOGGER.info("/findByPeriod Get mapping, метод контроллера : findUtilitiesByPeriod( )");
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

    //Удаление показаний по ID
    @PostMapping("{name}/delete")
    public  String deleteById(@RequestParam("utilityId") String utilityId, @PathVariable String name) {
        LOGGER.info("/delete Post Mapping, метод контроллера : deleteById( )");
        ur.setUtilityId(Long.parseLong(utilityId));
        utilityService.deleteById(ur);
        return "redirect:/utility/" + name;
    }

    //Добавление показаний счетчиков
    @PostMapping("{name}/save")
    public String saveUtility(Utilities utility, @PathVariable String name,  User user) {
        LOGGER.info("/save Post Mapping, метод контроллера : saveUtility( )");
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


    //Изменение текущих показаний счетчиков
    @PutMapping(value = "{name}/update" )
    public String updateUtility(Utilities utility, @PathVariable String name) {
        LOGGER.info("/update Put Mapping, метод контроллера : updateUtility( )");
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


