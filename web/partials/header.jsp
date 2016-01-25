<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>

<style>
    .mdl-navigation__link.logout-link {
        border: 0;
        margin: 0;
        padding-top: 11px;
        background-color: inherit;
    }
</style>

<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <a class="mdl-navigation__link" href="../home.jsp">
            <fmt:message key="header.info"/>
        </a>

        <div class="mdl-layout-spacer"></div>

        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="../home.jsp">
                <fmt:message key="navigation.home"/>
            </a>

            <c:if test="${currentUser eq null}">
                <a class="mdl-navigation__link" href="../login.jsp">
                    <fmt:message key="navigation.login"/>
                </a>
                <a class="mdl-navigation__link" href="../register.jsp">
                    <fmt:message key="navigation.register"/>
                </a>
            </c:if>

            <c:if test="${currentUser ne null && currentUser.admin}">
                <a class="mdl-navigation__link" href="../admin.jsp"><fmt:message key="navigation.manage"/></a>
            </c:if>

            <a class="mdl-navigation__link" href="../books.jsp"><fmt:message key="navigation.books"/></a>

            <c:if test="${currentUser ne null}">
                <a class="mdl-navigation__link" href="../profile.jsp">
                    <fmt:message key="navigation.profile"/>
                </a>

                <form action="home">
                    <input type="hidden" name="command" value="logout">
                    <input class="logout-link mdl-navigation__link"
                           value="<fmt:message key="navigation.logout" />"
                           type="submit"/>
                </form>
                <c:if test="${currentOrder ne null}">
                    <a class="mdl-navigation__link" href="../order.jsp">
                        <span class="mdl-badge" data-badge="${currentOrder.orderParts.size()}">
                            <fmt:message key="navigation.order"/>
                        </span>
                    </a>
                </c:if>

                <c:if test="${currentOrder eq null}">
                    <a class="mdl-navigation__link" href="../order.jsp">
                        <span class="mdl-badge" data-badge="0">
                            <fmt:message key="navigation.order"/>
                        </span>
                    </a>
                </c:if>
            </c:if>
        </nav>
    </div>
</header>