<#-- @ftlvariable type="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable type="form" type="com.khohlov.khohlov.domain.model.dummies_forms.CountryForm" -->

<#import  "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new country</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new country</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="" required autofocus/>
    </div>
    <button type="submit">Save</button>
</form>

<@spring.bind "form" />
<#if spring.status.error>
<ul>
    <#list spring.status.errorMessages as error>
        <li>${error}</li>
    </#list>
</ul>
</#if>

</body>
</html>