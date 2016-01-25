<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="btn" uri="/buttonTag" %>

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

    <main class="mdl-layout__content">

        <div class="mdl-grid" style="min-height: 76%;">
            <c:forEach items="${books}" var="book">
                <div class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--3-col mdl-color--yellow-100" style="height: 275px;">
                    <div class="mdl-card__title mdl-card--expand" style="background: url('${book.image}')
                            center center no-repeat; background-size: contain; margin-top: 3px">
                        <c:if test="${currentUser ne null}">
                            <btn:formBtn action="home" command="addOrderMediator" objId="${book.id}"
                                             styleClass="mdl-button mdl-js-button  mdl-button--fab mdl-button--mini-fab
                                                         mdl-button--colored">
                                <i class="material-icons">add</i>
                            </btn:formBtn>
                        </c:if>
                    </div>

                    <div class="mdl-card__supporting-text" style="color: #000000; font-size: 13px;">
                        <span class="title">
                            <fmt:message key="book.name"/>
                        </span><c:out value="${book.name}"/>
                        <br>
                        <span class="title">
                            <fmt:message key="book.author"/>
                        </span><c:out value="${book.author}"/>
                        <br>
                        <span class="title">
                            <fmt:message key="book.description"/>
                        </span><c:out value="${book.description}"/>
                        <br>
                        <span class="title">
                            <fmt:message key="book.published"/>
                        </span><c:out value="${book.published}"/>
                    </div>
                </div>
            </c:forEach>
        </div>

        <jsp:include page="partials/footer.jsp" />
    </main>
</div>
</body>
</html>
