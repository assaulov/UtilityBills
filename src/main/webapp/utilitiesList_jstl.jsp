<%--
  Created by IntelliJ IDEA.
  User: NOUT
  Date: 07.01.2021
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSP Page</title>
</head>

    <body>
<h3>List of Utilities ${today}</h3>

<form action="utilitiesList" method="post">
    <input type="text" name="user_id">
    <input type="submit" value="Отправить запрос">

</form>



<table border="1">
    <tbody>
    <caption>Коммуналка</caption>
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
    <c:forEach var="utility" items="${utilities}" varStatus="status">
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

</body>
</html>
