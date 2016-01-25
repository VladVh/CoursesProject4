<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>

<footer class="mdl-mini-footer mdl-color--blue-grey-600  mdl-color-text--white">
    <div class="mdl-mini-footer--left-section">
        <div class="mdl-logo">
            <fmt:message key="footer.info"/>
        </div>

        <ul class="mdl-mini-footer__link-list">
            <li>
                <form>
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="optionLang1">
                        <input type="radio" id="optionLang1" class="mdl-radio__button" onchange="submit()"
                               name="language" value="en" checked ${language == 'en' ? 'checked' : ''}>
                        <span class="mdl-radio__label mdl-color-text--white">EN</span>
                    </label>
                    <label class="mdl-radio mdl-js-radio mdl-js-ripple-effect" for="optionLang2">
                        <input type="radio" id="optionLang2" class="mdl-radio__button" onchange="submit()"
                               name="language" value="ua" ${language == 'ua' ? 'checked' : ''}>
                        <span class="mdl-radio__label mdl-color-text--white">UA</span>
                    </label>
                </form>
            </li>
        </ul>
    </div>
    <div class="mdl-mini-footer--right-section">
        <ul class="mdl-mini-footer__link-list">
            <li>
                <a href="https://vk.com/id18863847">
                    <fmt:message key="footer.created"/> <fmt:message key="footer.creator"/>
                </a>
            </li>
        </ul>
    </div>
</footer>
