
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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="https://www.thymeleaf.org"
      lang="ru">
<head>

    <title>Title</title>
    <link href="<c:url value="/css/style.css"/>" type="text/css" rel="stylesheet"/>
    <link href="<c:url value="/css/forForm.css"/>" type="text/css" rel="stylesheet"/>
</head>
<body>
<h3>${pageContext.request.userPrincipal.name}  ${today}</h3>
<a href="${pageContext.request.contextPath}/">Главная</a>


<form action="${pageContext.request.contextPath}/utility/${pageContext.request.userPrincipal.name}/findByDate" method="GET">
    <label>
      Поиск показаний по дате:  <input type="date" name="findByDate">
    </label>
    <input type="submit" value="Отправить запрос">

</form>

<p><span style="font-size: large; color: #ff0000; font-family: Arial; ">
<c:out value="${str}"/>
</span>
</p>


<form action="${pageContext.request.contextPath}/utility/${pageContext.request.userPrincipal.name}/findByPeriod" method="GET">
    <label>
       Начало периода: <input type="date" name="dateFrom">
                         -
        <input type="date" name="dateTo"> :Конец периода
    </label>
    <input type="hidden" name="${pageContext.request.userPrincipal.name}" >
    <input type="submit" value="Отправить запрос">

</form>

<%--
<button onclick="document.location='${pageContext.request.contextPath}/utility/ALL'">Показания счетчиков всех пользователей </button>
--%>



<button id="1" onclick='openForm(this.id)'> Показать форму</button>



<p><span style="font-size: large; color: #ff0000; font-family: Arial; align-content: center ">
<c:out value="${success}"/>
</span>
</p>

<p><span style="font-size: large; color: #ff0000; font-family: Arial; align-content: center ">
<c:out value="${fail}"/>
</span>
</p>
<br>
<table      border="1">
    <tbody align="center">
    <caption>
        <p>
            <span style="font-size: 40px; color: #1e048e; font-family: 'Monotype Corsiva',serif; ">
                <c:out value="Таблица показаний счетчиков "/>
            </span>
        </p>
        <p>
            <span style="font-size: large; color: #000000; font-family: Arial; ">

                <c:if test="${not empty id}">
                     <c:out value="Показания счетчиков для пользователя с ID: ${id}"/>
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
        <th width="60">DELETE</th>
        <th width="60">UPDATE</th>
    </tr>
    </thead>
    <c:if test="${not empty utilities}">
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
            <td> <form action="${pageContext.request.contextPath}/utility/${pageContext.request.userPrincipal.name}/delete" name="utilityId" method="post" >
                <input type="hidden" name="utilityId" value="${utility.utilityId}">
                <input type="submit" value="Delete" />
            </form>
            </td>
            <td>

                <input type="button" value="Update" id="${utility.utilityId+1}" onclick="updateDorm(this.id)"/>
            </td>
        </tr>
    </c:forEach>
    </c:if>
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
<div class="popup">
    <div class="popup__container">
        <div class="popup__wrapper">
            <div id="blablabla">

                <form role="form" action="${pageContext.request.contextPath}/utility/${pageContext.request.userPrincipal.name}/save" autocomplete="off" method="POST">


                    <input type="hidden" name= "${pageContext.request.userPrincipal.name}">
                    <br>
                    <label>Дата списания счетчиков:</label>
                    <input type="date" name="dateOfWriteUtilityMeter" autofocus required>

                    <br>
                    <label>Холодная вода:</label>
                    <input type="text" name="coldWater" placeholder = "Введите 0 если нет показаний" required pattern="^[ 0-9]+$"  >
                    <br>
                    <label>Горячая вода:</label>
                    <input type="text" name="hotWater" required pattern="^[ 0-9]+$">
                    <br>
                    <label>Электричество:</label>
                    <input type="text" name="electricity" required pattern="^[ 0-9]+$">
                    <br>
                    <label>Газ:</label>
                    <input type="text" name="gas" required pattern="^[ 0-9]+$">
                    <br>
                    <label>Общедомовые услуги:</label>
                    <input type="text" name="houseUtility" required pattern="^[ 0-9]+$">
                    <br>
                    <label>Капитальный ремонт:</label>
                    <input type="text" name="capitalRepair" required pattern="^[ 0-9]+$">
                    <br>
                    <input type="submit" value="Отправить запрос" >

                </form>
            </div>
        </div>
    </div>
</div>

<%--Это костыль по отображению данных для изменения, но как сделать по другому без использования другой страницы jsp и javascript с json запросом я не знаю. --%>
<c:if test="${not empty utilities}">
<div class="updade">
    <div class="updade__container">
        <div class="updade__wrapper">
            <div id="updadeID">
                <c:forEach var="utility" items="${utilities}" varStatus="status" >
                <form action="${pageContext.request.contextPath}/utility/${pageContext.request.userPrincipal.name}/update" method="POST">
                    <input type="hidden" name="_method" value="PUT"/>

                   <table>
                    <tr>
                    <th>${status.count}</th>
                    <th>${utility.dateOfWriteUtilityMeter}</th>
                    <th> <input type="text" name="coldWater" required pattern="^[ 0-9]+$" value="${utility.coldWater}"></th>
                    <th><input type="text" name="hotWater" required pattern="^[ 0-9]+$" value="${utility.hotWater}"></th>
                    <th><input type="text" name="electricity" required pattern="^[ 0-9]+$"value="${utility.electricity}"></th>
                    <th><input type="text" name="gas" required pattern="^[ 0-9]+$" value="${utility.gas}"></th>
                    <th><input type="text" name="houseUtility" required pattern="^[ 0-9]+$" value="${utility.houseUtility}"></th>
                    <th><input type="text" name="capitalRepair" required pattern="^[ 0-9]+$" value="${utility.capitalRepair}"></th>


                    <input type="hidden" name="utilityId" value="<c:out value="${utility.utilityId}"/>">


                    </tr>
                    </table>
                    <input type="submit" value="Отправить запрос">

                </form>

             </c:forEach>
            </div>
        </div>
    </div>
</div>

</c:if>

<script>
    function openForm(clicked_id) {
        const button = document.getElementById(clicked_id);
        const form = document.querySelector('#blablabla');
        const popup = document.querySelector('.popup');
        const html = document.querySelector('html');


        button.addEventListener('click', () => {
            form.classList.add('open');
            popup.classList.add('popup_open');
            setTimeout(function () {
                popup.classList.remove("popup_hide");
            }, 100);
        });

        html.addEventListener('click', function (e) {
            if (e.target.tagName !== 'FORM' && e.target.tagName !== 'LABEL' && e.target.tagName !== 'INPUT') {
                popup.classList.add("popup_hide");
            }
        });
    }


    function updateDorm(clicked_id) {
        const button = document.getElementById(clicked_id);
        const form = document.querySelector('#updadeID');
        const updade = document.querySelector('.updade');
        const html = document.querySelector('html');


        button.addEventListener('click', () => {
            form.classList.add('open');
            updade.classList.add('updade_open');
            setTimeout(function () {
                updade.classList.remove("updade_hide");
            }, 100);
        });

        html.addEventListener('click', function (e) {
            if (e.target.tagName !== 'FORM' && e.target.tagName !== 'LABEL' && e.target.tagName !== 'INPUT') {
                updade.classList.add("updade_hide");
            }
        });
    }


</script>


</body>
</html>
