
<%--
  Created by IntelliJ IDEA.
  User: NOUT
  Date: 17.01.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title</title>
    <style>
        table {


            margin: auto; /* Выравниваем таблицу по центру окна  */
        }
        td {
            text-align: center; /* Выравниваем текст по центру ячейки */
        }
    </style>
</head>
<body>
<h3>List of Utilities ${today}</h3>

<p><span style="font-size: large; color: #79ff00; font-family: Arial; ">
<c:out value="${userror}"/>
</span>
</p>

<form action="${pageContext.request.contextPath}/mvc/utility/userId" method="GET">
    <label>
        <input type="text" name="userId">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<form action="${pageContext.request.contextPath}/mvc/utility/findByDate" method="GET">
    <label>
        <input type="date" name="findByDate">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<p><span style="font-size: large; color: #ff0000; font-family: Arial; ">
<c:out value="${str}"/>
</span>
</p>


<form action="${pageContext.request.contextPath}/mvc/utility/findByPeriod" method="GET">
    <label>
        <input type="date" name="dateFrom">
        <input type="date" name="dateTo">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<button onclick="document.location='${pageContext.request.contextPath}/mvc/utility/ALL'">Показать всю комуналку, всех пользователей </button>



<table border="1">
    <tbody align="center">
    <caption>
        <p>
            <span style="font-size: 40px; color: #1e048e; font-family: 'Monotype Corsiva'; ">
                <c:out value="Таблица показаний счетчиков "/>
        <p>
            <span style="font-size: large; color: #000000; font-family: Arial; ">

                <c:if test="${not empty userId}">
                     <c:out value="Коммунальные счета для пользователя с ID: ${userId}"/>
                </c:if>
                <c:if test="${not empty date}">
                    <c:out value="Коммунальные счета по дате: ${date}"/>
                </c:if>
                <c:if test="${not empty dateFrom}">
                    <c:if test="${not empty dateTo}">
                        <c:out value="Коммунальные счета за период: ${dateFrom} - ${dateTo}"/>
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
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:choose>
    <c:when test="${empty utilities}">
        <p align="center">
            <span style="font-size: large; color: #0069e3; font-family: Arial; ">
                <c:out value="Коммунальные счета c заданными параметрами не найдены!"/>
            </span>
        </p>
    </c:when>


</c:choose>

</body>
</html>
