package edu.project.utility_bills.controllers;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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



    @GetMapping
    public ModelAndView showPage() {
     ModelAndView model = new ModelAndView();
        model.setViewName("utility");
        LOGGER.info("Начальное отображение страницы");
       return model;
    }


    @GetMapping("/ALL")
    public ModelAndView findAllUtilitiesOfAllUsers( ) {
        LOGGER.info("/ALL Get mapping, метод контроддера : findAllUtilitiesOfAllUsers( )");
        List<UtilityResponse> utilitiesList = utilityService.findAll();
        ModelAndView model = new ModelAndView();
        model.setViewName("utility");
        model.addObject("utilities", utilitiesList);
        model.addObject("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        return model;
    }

    @GetMapping("/findByUserId")
    public ModelAndView findUtilitiesByUserId(ModelAndView model,  @RequestParam("userId") String userId) throws NullPointerException{
        LOGGER.info("/findByUserId Get mapping, метод контроддера : findUtilitiesByUserId( )");
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

    @GetMapping("/findByPeriod")
    public  ModelAndView findUtilitiesByPeriod(ModelAndView model,
                                         @RequestParam("dateFrom") String dateFrom,
                                         @RequestParam("dateTo") String dateTo) {
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

    @PostMapping("/delete")
    public @ResponseBody ModelAndView deleteByDate(@RequestParam("date") LocalDate date) {
        LOGGER.info("/delete Post Mapping, метод контроддера : deleteByDate( )");
        ur.setDateOfWriteUtilityMeter(date);
        utilityService.deleteByDate(ur);
    return findAllUtilitiesOfAllUsers();
    }


    @PostMapping("/save")
    public @ResponseBody ModelAndView saveUtility(ModelAndView model, Utilities utility) {
        LOGGER.info("/save Post Mapping, метод контроддера : saveUtility( )");

        model.addObject("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        model.addObject("utilities", "");
        model.setViewName("utility");
              try{
                        utilityService.addUtility(utility);
                        model.addObject("success", "Данные успешно внесены");
                                       return model;
                } catch (Exception e) {
            model.addObject("fail", "Ошибка добавления данных. Проверь ввод.");
            return model;
        }
    }



    @PutMapping(value = "/update" , consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView updateUtility(Utilities utility) {
        LOGGER.info("/update Put Mapping, метод контроддера : updateUtility( )");
        LOGGER.info("Контроллер начинает работу");
        LOGGER.info(String.valueOf(utility.getUtilityId())+ " ID счетчиков");


        ur.setUtilityId(utility.getUtilityId());
        LOGGER.info(ur.toString() + "Запрос контроллера");

        try {
            LOGGER.info("Запрос ушел в сервис ");
            utilityService.updateById(ur,utility);
            LOGGER.info("Вернулся ответ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/utility");

    }
}


