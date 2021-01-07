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
    <p>UserId: <input type="text" th:field="*{user_id}" /></p>
    <input type="submit" value="login">

</form>







<table border="1">
    <tbody>
    <c:forEach var="utility" items="${utilities}" varStatus="status">

        <c:if test="${status.count%2==1}">
            <tr style="background-color: yellow;">
        </c:if>
        <c:if test="${status.count%2!=1}">
            <tr style="background-color: green;">
        </c:if>

        <td>${status.count}</td>
        <td>Дата : ${utility.dateOfWriteUtilityMeter}</td>
        <td>Холодная вода : ${utility.coldWater}</td>
        <td>Горячая вода : ${utility.hotWater}</td>
        <td>Электричество : ${utility.electricity}</td>
        <td>Газ : ${utility.gas}</td>
        <td>Общедомовые: ${utility.houseUtility}</td>
        <td>Капитальный ремонт : ${utility.capitalRepair}</td>


        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
