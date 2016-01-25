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
        min-height: 76%;
        padding: 10px;
    }
    .mdl-data-table {
        margin: 10px;
    }
</style>

<html lang="${language}">
<jsp:include page="partials/meta.jsp"/>

<body class="mdl-color--grey-100 mdl-color-text--black mdl-base">
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
    <jsp:include page="partials/header.jsp"/>

    <main class="mdl-layout__content">
        <div class="mdl-card mdl-shadow--6dp">
            <c:if test="${fn:length(currentOrder.orderParts) ne 0 && currentOrder ne null}">
                <table class="mdl-data-table mdl-js-data-table">
                    <h3>
                        <fmt:message key="order.header"/>
                    </h3>
                    <thead>
                    <tr class="table-head-row">
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="book.name"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="book.author"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="book.description"/>
                        </th>
                        <th class="mdl-data-table__cell--non-numeric">
                            <fmt:message key="book.published"/>
                        </th>
                        <th>
                            <fmt:message key="order.book.count"/>
                        </th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:set var="counter" value="0" scope="page"/>
                    <c:forEach items="${currentOrder.orderParts}" var="orderMediator">
                        <tr>
                            <td>
                                    ${orderMediator.book.name}
                            </td>
                            <td>
                                    ${orderMediator.book.author}
                            </td>
                            <td>
                                    ${orderMediator.book.description}
                            </td>
                            <td>
                                    ${orderMediator.book.published}
                            </td>
                            <td>
                                    ${orderMediator.count}
                            </td>
                            <td>
                                <btn:formBtn action="home" command="deleteOrderMediator" objId="${counter}"
                                             styleClass="mdl-button mdl-js-button mdl-button--fab
                                                                     mdl-button--colored">
                                    <i class="material-icons">remove circle</i>
                                </btn:formBtn>
                            </td>
                            <c:set var="counter" value="${counter + 1}"/>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <form action="home" method="post">
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-1">
                        <input type="radio" id="option-1" class="mdl-radio__button" name="options" value="1" checked>
                        <span class="mdl-radio__label">
                            <fmt:message key="order.library"/>
                        </span>
                    </label>
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="option-2">
                        <input type="radio" id="option-2" class="mdl-radio__button" name="options" value="2">
                        <span class="mdl-radio__label">
                            <fmt:message key="order.home"/>
                        </span>
                    </label>
                    <input type="hidden" name="command" value="addOrder">
                    <button class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                        <fmt:message key="order.add"/>
                    </button>
                </form>
            </c:if>
            <c:if test="${fn:length(currentOrder.orderParts) eq 0 || currentOrder eq null}">
                <h2>
                    <fmt:message key="order.empty"/>
                </h2>
            </c:if>
        </div>
    </main>

    <jsp:include page="partials/footer.jsp"/>
</div>
</body>
</html>
