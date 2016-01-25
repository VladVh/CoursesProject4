<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <main class="login-content mdl-layout__content">
        <div class="mdl-card mdl-shadow--6dp">
            <form action="home" method="post">
                <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                    <h2 class="mdl-card__title-text">
                        <fmt:message key="input.info"/>
                    </h2>
                </div>
                <div class="mdl-card__supporting-text">
                    <input type="hidden" name="command" value="login">

                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="text" name="email" id="log-email" required="true"/>
                        <label class="mdl-textfield__label" for="log-email">
                            <fmt:message key="input.email"/>
                        </label>
                    </div>
                    <div class="custom-label mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <input class="mdl-textfield__input" type="password" name="password" id="log-pass"
                               required="true"/>
                        <label class="mdl-textfield__label" for="log-pass">
                            <fmt:message key="input.password"/>
                        </label>
                    </div>
                </div>
                <div class="mdl-card__actions mdl-card--border">
                    <button type="submit"
                            class="mdl-button mdl-button--colored mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                        <fmt:message key="input.login"/>
                    </button>
                </div>
            </form>
        </div>

    </main>
    <div class="footer">
        <jsp:include page="partials/footer.jsp"/>
    </div>

</div>
</body>
</html>