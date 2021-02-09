package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.User;
import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("utility")
public class UtilityListController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UtilityListController.class);


    @Autowired
    private UtilityService utilityService;

    @Autowired
    private UtilityRequest ur;



    @GetMapping("/{name}")
    public ModelAndView findAll(@PathVariable String name) {
        LOGGER.info("/ALL Get mapping, метод контроддера : showPage( )");
        ur.setUsername(name);
        List<UtilityResponse> utilitiesList = utilityService.findAll(ur);
        ModelAndView model = new ModelAndView();
        model.setViewName("utility");
        model.addObject("utilities", utilitiesList);
        model.addObject("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return model;
    }


    @GetMapping("{name}/findByDate")
    public ModelAndView findUtilitiesByDate(ModelAndView model, @RequestParam("findByDate") String date, @PathVariable String name) {
        LOGGER.info("/findByDate Get mapping, метод контроддера : findUtilitiesByDate( )");
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

    @GetMapping("{name}/findByPeriod")
    public  ModelAndView findUtilitiesByPeriod(ModelAndView model,
                                               @RequestParam("dateFrom") String dateFrom,
                                               @RequestParam("dateTo") String dateTo, @PathVariable String name) {
        LOGGER.info("/findByPeriod Get mapping, метод контроддера : findUtilitiesByPeriod( )");
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

    @PostMapping("{name}/delete")
    public @ResponseBody ModelAndView deleteById(@RequestParam("utilityId") String utilityId, @PathVariable String name) {
        LOGGER.info("/delete Post Mapping, метод контроддера : deleteById( )");
        ur.setUtilityId(Long.parseLong(utilityId));
        utilityService.deleteById(ur);
    return findAll(name);
    }


    @PostMapping("{name}/save")
    public @ResponseBody ModelAndView saveUtility(Utilities utility, @PathVariable String name,  User user) {
        LOGGER.info("/save Post Mapping, метод контроддера : saveUtility( )");

        System.out.println(name);
        user.setUsername(name);
        utility.setUser(user);
              try{        LOGGER.info(" try  utilityService.save(utility)");


                  utilityService.saveUtilities(utility);
                  return findAll(name);
                } catch (Exception e) {

                  LOGGER.info(e.toString());
            return findAll(name);
        }
    }



    @PutMapping(value = "{name}/update" )
    public ModelAndView updateUtility(Utilities utility, @PathVariable String name) {
        LOGGER.info("/update Put Mapping, метод контроддера : updateUtility( )");
        LOGGER.info("Контроллер начинает работу");



           ur.setUtilityId(utility.getUtilityId());
            LOGGER.info(String.valueOf(utility.getUtilityId())+ " ID счетчиков");
            LOGGER.info(ur.toString() + "Запрос контроллера");
            LOGGER.info("Запрос ушел в сервис ");
            utilityService.updateById(ur,utility);
            LOGGER.info("Вернулся ответ");


         return new ModelAndView(new RedirectView(""));

    }


}


