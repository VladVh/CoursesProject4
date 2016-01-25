<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="i18n.text" />

<html lang="${language}">
    <jsp:include page="partials/meta.jsp" />

    <body class="mdl-color--grey-100 mdl-color-text--black mdl-base">
        <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
            <jsp:include page="partials/header.jsp" />

            <main class="reg-content mdl-layout__content">
                <div class="mdl-card mdl-shadow--6dp">
                    <form action="home" method="post" style="text-align: center">
                    <div class="my-title mdl-card__title mdl-color--primary mdl-color-text--white">
                        <h2 class="mdl-card__title-text">
                            <fmt:message key="input.info" />
                        </h2>
                    </div>
                    <div class="mdl-card__supporting-text">
                            <input type="hidden" name="command" value="register">

                            <div class="mdl-textfield mdl mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="text" id="reg-fir-name"
                                       name="firstName" pattern="[a-zA-Z\u0400-\u04ff]{2,20}" required="true" />
                                <label class="mdl-textfield__label" for="reg-fir-name">
                                    <fmt:message key="input.name.first" />
                                </label>
                                <span class="mdl-textfield__error">
                                        <fmt:message key="input.name.first.error" />
                                </span>
                            </div>

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input type="text" class="mdl-textfield__input" id="reg-last-name"
                                       name="secondName" pattern="[a-zA-Z\u0400-\u04ff]{2,20}" required="true">
                                <label for="reg-last-name" class="mdl-textfield__label">
                                    <fmt:message key="input.name.last" />
                                </label>
                                <span class="mdl-textfield__error">
                                        <fmt:message key="input.name.last.error" />
                                </span>
                            </div>

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="email" id="reg-email" name="email" required="true" />
                                <label class="mdl-textfield__label" for="reg-email">
                                    <fmt:message key="input.email" />
                                </label>
                            </div>

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                                <input class="mdl-textfield__input" type="password" id="log-pass"
                                       name="password" pattern="[a-zA-Z0-9\u0400-\u04ff]{6,18}" required="true" />
                                <label class="mdl-textfield__label" for="log-pass">
                                    <fmt:message key="input.password" />
                                </label>
                                <span class="mdl-textfield__error">
                                        <fmt:message key="input.password.error" />
                                </span>
                            </div>
                    </div>
                    <div class="mdl-card__actions mdl-card--border">
                        <button type="submit" style="float: left"
                                class="mdl-button mdl-button--colored mdl-js-button mdl-button--raised mdl-js-ripple-effect">
                            <fmt:message key="input.register" />
                        </button>
                    </div>
                    </form>
                </div>
            </main>

            <jsp:include page="partials/footer.jsp" />
        </div>
    </body>
</html>