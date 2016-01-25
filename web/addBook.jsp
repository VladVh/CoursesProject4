<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : 'en'}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>

<div class="mdl-card mdl-shadow--2dp">
    <form action="home" method="post" style="text-align: center">
        <input type="hidden" name="command" value="addBook">

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <label for="book_name" class="mdl-textfield__label">
                <fmt:message key="book.name"/>
            </label>
            <input type="text" class="mdl-textfield__input " id="book_name" name="name"
                   pattern="[a-zA-Z\u0400-\u04ff0-9\s]{4,30}" required>
                <span class="mdl-textfield__error">
                    <fmt:message key="book.name.error"/>
                </span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <label for="book_author" class="mdl-textfield__label">
                <fmt:message key="book.author"/>
            </label>
            <input type="text" class="mdl-textfield__input" id="book_author" name="author"
                   pattern="[a-zA-Z\u0400-\u04ff\s]{4,30}" required>
            <span class="mdl-textfield__error">
                    <fmt:message key="book.author.error"/>
            </span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <label for="book_descr" class="mdl-textfield__label">
                <fmt:message key="book.description"/>
            </label>
            <input type="text" class="mdl-textfield__input" id="book_descr" name="description"
                   pattern="{1,100}" required/>
            <span class="mdl-textfield__error">
                    <fmt:message key="book.description.error"/>
            </span>
        </div>

        <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
            <label for="book_img" class="mdl-textfield__label">
                <fmt:message key="book.image"/>
            </label>
            <input type="text" class="mdl-textfield__input" id="book_img" name="image" required/>
            <span class="mdl-textfield__error">
                    <fmt:message key="book.image.error"/>
            </span>
        </div>

        <br>
        <br>

        <button type="submit"
                class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
            <fmt:message key="book.add"/>
        </button>
    </form>
</div>
