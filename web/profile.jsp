<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="btn" uri="/buttonTag" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>

<style>
    .mdl-layout__content {
        padding: 24px;
        flex: none;
        margin-left: auto;
        margin-right: auto;
    }

    .mdl-layout__content > .mdl-card {
        width: auto;
        text-align: center;
        min-height: 76%;
        min-width: 400px;
    }

    .mdl-data-table {
        text-align: center;
        margin-left: auto;
        margin-right: auto;
    }
</style>

<html lang="${language}">
<jsp:include page="partials/meta.jsp"/>

<body class="mdl-color--grey-100 mdl-color-text--black mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="partials/header.jsp"/>

    <main class="mdl-layout__content">
        <div class="mdl-card mdl-shadow--6dp">
            <h3><fmt:message key="profile.order.returned"/></h3>
            <c:set var="noOrdersReturned" value="${currentUser.getCountReturned()}" scope="page"/>
            <c:if test="${noOrdersReturned eq 0}">
                <tr><fmt:message key="profile.order.returned.empty"/></tr>
            </c:if>
            <c:if test="${noOrdersReturned ne 0}">
                <table class="mdl-data-table mdl-js-data-table">
                    <thead>
                    <tr class="table-head-row">
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.count"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.made"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.read"/>
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${currentUser.orders}" var="order">
                        <c:if test="${order.returned}">
                            <tr>
                                <td>${fn:length(order.orderParts)}</td>
                                <td>${order.date}</td>
                                <td>
                                    <c:if test="${order.atLibrary}">
                                        <fmt:message key="profile.yes"/>
                                    </c:if>
                                    <c:if test="${not order.atLibrary}">
                                        <fmt:message key="profile.no"/>
                                    </c:if>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <hr size="4px">

            <h3><fmt:message key="profile.order.notreturned"/></h3>
            <c:set var="noOrdersNotReturned" value="${currentUser.getCountNotReturned()}" scope="page"/>
            <c:if test="${noOrdersNotReturned eq 0}">
                <tr>
                    <td><fmt:message key="profile.order.notreturned.empty"/></td>
                </tr>
            </c:if>
            <c:if test="${noOrdersNotReturned ne 0}">
                <table class="mdl-data-table mdl-js-data-table">
                    <thead>
                    <tr class="table-head-row">
                        <th><fmt:message key="profile.order.count"/></th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.made"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.read"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="profile.order.returned"/>
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${currentUser.orders}" var="order">
                        <c:if test="${not order.returned}">
                            <tr>
                                <td>${fn:length(order.orderParts)}</td>
                                <td class="mdl-data-table__cell--non-numeric">${order.date}</td>
                                <td>
                                    <c:if test="${order.atLibrary}">
                                        <fmt:message key="profile.yes"/>
                                    </c:if>
                                    <c:if test="${not order.atLibrary}">
                                        <fmt:message key="profile.no"/>
                                    </c:if>
                                </td>
                                <td>
                                    <btn:formBtn action="home" command="returnBooks" objId="${order.id}"
                                                     styleClass="mdl-button mdl-js-button mdl-button--icon mdl-button--colored">
                                        <i class="material-icons">check</i>
                                    </btn:formBtn>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>

    </main>
    <jsp:include page="partials/footer.jsp"/>

</div>
</body>
</html>