<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>

<html lang="${language}">
<jsp:include page="partials/meta.jsp"/>

<body class="mdl-color--grey-100 mdl-color-text--black mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="partials/header.jsp"/>
    <div class="mdl-grid"  style="min-height: 76%; padding: 24px">

        <main class="mdl-layout__content mdl-cell mdl-cell--4-col ">
            <c:if test="${fn:length(users) ne 0}">
            <table class="mdl-data-table mdl-js-data-table">
                <h3>
                    <fmt:message key="admin.heading"/>
                </h3>
                <thead>
                <tr class="table-head-row">
                    <th class="mdl-data-table__cell--non-numeric">
                        <fmt:message key="admin.table.name"/>
                    </th>
                    <th class="mdl-data-table__cell--non-numeric">
                        <fmt:message key="admin.table.email"/>
                    </th>
                    <th>
                        <fmt:message key="admin.table.orders"/>
                    </th>
                    <th>
                        <fmt:message key="admin.table.notreturned"/>
                    </th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td class="mdl-data-table__cell--non-numeric">
                                ${user.firstName} ${user.secondName}
                        </td>
                        <td class="mdl-data-table__cell--non-numeric">
                                ${user.email}
                        </td>
                        <td>
                                ${fn:length(user.orders)}
                        </td>
                        <td>
                                ${user.getCountNotReturned()}
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:if>
        </main>
        <div class="mdl-cell mdl-cell--4-col">
            <jsp:include page="addBook.jsp"/>
        </div>
    </div>
    <jsp:include page="partials/footer.jsp"/>
</div>
</body>
</html>