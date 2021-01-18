
<%--
  Created by IntelliJ IDEA.
  User: NOUT
  Date: 17.01.2021
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title</title>
</head>
<body>
<h3>List of Utilities ${today}</h3>

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

<style type="text/css">
#dt {
text-indent: -500px;
height: 25px;
width: 200px;
}
</style>

<button onclick="document.location='${pageContext.request.contextPath}/mvc/utility/ALL'">Показать всю комуналку, всех пользователей </button>

<input type="date" id="dt" onchange="mydate1();" hidden/>
<input type="text" id="ndt"  onclick="mydate();" hidden />
<input type="button" Value="Date" onclick="mydate();" />
<script>
function mydate() {
//alert("");
document.getElementById("dt").hidden = false;
document.getElementById("ndt").hidden = true;
}

function mydate1() {
d = new Date(document.getElementById("dt").value);
dt = d.getDate();
mn = d.getMonth();
mn++;
yy = d.getFullYear();
document.getElementById("ndt").value = dt + "/" + mn + "/" + yy
document.getElementById("ndt").hidden = false;
document.getElementById("dt").hidden = true;
}
</script>


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
