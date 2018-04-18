<#-- @ftlvariable type="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable type="form" type="com.khohlov.khohlov.domain.model.dummies_forms.CompanyForm" -->
<#-- @ftlvariable type="countries" type="java.util.List<com.khohlov.khohlov.domain.model.Country>" -->

<#import  "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new company</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new Company</h1>

<form role="form" type="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="" required autofocus/>
    </div>
    <div>
        <label for="phone">Phone (Only characters 0-9)</label>
        <input type="text" name="phone" id="phone" value="${form.phone}" required />
    </div>
    <div>
        <label for="adres">Adress</label>
        <input type="text" name="adres" id="adres" value="${form.adres}" required />
    </div>
    <div>
        <label for="site">Web site</label>
        <input type="text" name="site" id="site" value="${form.site}" required />
    </div>
    <div>
        <label for="country">Country</label>

        <select name="country" id="country" required>
            <#list countries as val>
                <option value="${val.name}" <#if form.country == val.name>selected</#if> >${val.name}</option>
            </#list>
        </select>

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