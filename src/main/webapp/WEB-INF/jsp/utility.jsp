
<%--
  Created by IntelliJ IDEA.
  User: NOUT
  Date: 17.01.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="https://www.thymeleaf.org"
      lang="ru">
<head>
    <meta name="viewport" ontent="width=device-width, initial-scale=1">
    <title>Title</title>
    <style>
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            font-size: 14px;
            border-collapse: collapse;
            text-align: center;
            margin: auto;
        }
        th, td:first-child {
            background: #AFCDE7;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
            text-align: center;
        }
        td {
            background: #D8E6F3;

        }
        th:first-child, td:first-child {
            text-align: center;
        }

        input[type=text] {
            width: 10%;
            padding: 6px 10px;
            margin: 8px 0;
            box-sizing: border-box ;
            border: 3px solid #ccc;
            -webkit-transition: 0.5s;
            transition: 0.5s;
            outline: none;
        }
        input[type=date] {
            width: 10%;
            padding: 6px 10px;
            margin: 8px 0;
            box-sizing: border-box ;
            border: 3px solid #ccc;
            -webkit-transition: 0.5s;
            transition: 0.5s;
            outline: none;
        }


        input[type=text]:focus {
            border: 3px solid #555;
        }
    </style>
</head>
<body>
<h3>List of Utilities ${today}</h3>

<p><span style="font-size: large; color: #79ff00; font-family: Arial; ">
<c:out value="${userror}"/>
</span>
</p>

<form action="${pageContext.request.contextPath}/utility/findByUserId" method="get">

    <label>
        Поиск по ID пользователя: <input type="text" name="userId">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<form action="${pageContext.request.contextPath}/utility/findByDate" method="GET">
    <label>
      Поиск показаний по дате:  <input type="date" name="findByDate">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<p><span style="font-size: large; color: #ff0000; font-family: Arial; ">
<c:out value="${str}"/>
</span>
</p>


<form action="${pageContext.request.contextPath}/utility/findByPeriod" method="GET">
    <label>
       Начало периода: <input type="date" name="dateFrom">
                         -
        <input type="date" name="dateTo"> :Конец периода
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<button onclick="document.location='${pageContext.request.contextPath}/utility/ALL'">Показания счетчиков всех пользователей </button>



<table border="1">
    <tbody align="center">
    <caption>
        <p>
            <span style="font-size: 40px; color: #1e048e; font-family: 'Monotype Corsiva'; ">
                <c:out value="Таблица показаний счетчиков "/>
        <p>
            <span style="font-size: large; color: #000000; font-family: Arial; ">

                <c:if test="${not empty userId}">
                     <c:out value="Показания счетчиков для пользователя с ID: ${userId}"/>
                </c:if>
                <c:if test="${not empty date}">
                    <c:out value="Показания счетчиков по дате: ${date}"/>
                </c:if>
                <c:if test="${not empty dateFrom}">
                    <c:if test="${not empty dateTo}">
                        <c:out value="Показания счетчиков за период: ${dateFrom} - ${dateTo}"/>
                    </c:if>
                </c:if>
            </span>
        </p>
    </caption>
    <thead>
    <tr>
        <th>Номер</th>
        <th>Дата</th>
        <th>Холодная</th>
        <th>Горячая</th>
        <th>Электричество</th>
        <th>Газ</th>
        <th>Общедомовые</th>
        <th>Капитальный ремонт</th>
        <th width="60">Delete</th>
    </tr>
    </thead>
    <c:forEach var="utility" items="${utilities}" varStatus="status" >

        <tr>

            <th>${status.count}</th>
            <th>${utility.dateOfWriteUtilityMeter}</th>
            <th>${utility.coldWater}</th>
            <th>${utility.hotWater}</th>
            <th>${utility.electricity}</th>
            <th>${utility.gas}</th>
            <th>${utility.houseUtility}</th>
            <th>${utility.capitalRepair}</th>
            <td> <form action="/utility/delete/" name="date" method="post" >
                <input type="hidden" name="date" value="${utility.dateOfWriteUtilityMeter}">
                <input type="submit" value="Delete" class="btn btn-danger" />
            </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:choose>
    <c:when test="${empty utilities}">
        <p align="center">
            <span style="font-size: large; color: #0069e3; font-family: Arial; ">
                <c:out value="Показания счетчиков c заданными параметрами не найдены!"/>
            </span>
        </p>
    </c:when>


</c:choose>

</body>
</html>
