

<%--
  Created by IntelliJ IDEA.
  User: NOUT
  Date: 07.01.2021
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="edu.project.utility_bills.domain.Utilities, java.util.List"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Utilities</title>
</head>
<body>
    <h1>List of Utilities ${param.today}</h1>


<%

    List<Utilities> list = (List<Utilities>) request.getAttribute("utilities");
    if(list != null) {
        for (Utilities u : list) {
            out.println(u.getDateOfWriteUtilityMeter());
            out.println(u.getColdWater());
            out.println(u.getHotWater());
            out.println(u.getElectricity());
            out.println(u.getGas());
            out.println(u.getHouseUtility());
            out.println(u.getCapitalRepair());
            out.println("<br/>");

        }
    } else out.println("Список Пуст!");
%>

</body>
</html>
