package edu.project.utility_bills.servlets;


import edu.project.utility_bills.domain.Utilities;
import edu.project.utility_bills.service.UtilityService;
import edu.project.utility_bills.view.UtilityRequest;
import edu.project.utility_bills.view.UtilityResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "UtilityListServlet", urlPatterns = {"/utilitiesList"})
public class UtilityListServlet extends HttpServlet {

      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          ServletContext ctx = getServletContext();
          WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(ctx);
          req.getSession().setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));

          getServletContext().getRequestDispatcher("/utilitiesList_jstl.jsp").forward(req,resp);

      }

    /* list.forEach(u -> System.out.println("Пользователь : "+ u.getUser().getPseudoName()+ " Дата списания счетчиков :" + u.getDateOfWriteUtilityMeter().toString() +
                "\n Показания счетчика холодной воды : "+ u.getColdWater() +
                "\n Показания счетчика горячей воды : " +u.getHotWater() +
                "\n Показания электрического счетчика : " + u.getElectricity() +
                "\n Показания газового счетчика : " + u.getGas() +
                "\n Общедомовые (руб) : "+u.getHouseUtility() +
                "\n Капитальный ремонт(руб): " + u.getCapitalRepair()));*/


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ServletContext ctx = getServletContext();
        WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(ctx);
        assert webCtx != null;
        UtilityService service = webCtx.getBean(UtilityService.class);
        UtilityRequest ur = new UtilityRequest();
        String param = req.getParameter("user_id");

        if(param.equals("ALL"))
        {
            List<Utilities> list  = service.findAll();
            req.getSession().setAttribute("utilities", list);
        }         else {
            ur.setUserId(Long.parseLong(param));
            List<UtilityResponse> list =  service.findUtilities(ur);
            req.getSession().setAttribute("utilities", list);

        }
        req.getSession().setAttribute("today", LocalDate.now().format(DateTimeFormatter.ISO_DATE));

        getServletContext().getRequestDispatcher("/utilitiesList_jstl.jsp").forward(req,resp);


    }


}

