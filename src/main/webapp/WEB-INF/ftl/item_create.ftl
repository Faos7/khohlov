<#-- @ftlvariable type="_csrf" type="org.springframework.security.web.csrf.CsrfToken" -->
<#-- @ftlvariable type="form" type="com.khohlov.khohlov.domain.model.dummies_forms.ItemForm" -->
<#-- @ftlvariable type="companies" type="java.util.List<com.khohlov.khohlov.domain.model.Company>" -->
<#-- @ftlvariable type="countries" type="java.util.List<com.khohlov.khohlov.domain.model.Country>" -->

<#import  "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create a new item</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
    </ul>
</nav>

<h1>Create a new Item</h1>

<form role="form" name="form" action="" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div>
        <label for="name">Name</label>
        <input type="text" name="name" id="name" value="" required autofocus/>
    </div>
    <div>
        <label for="type">Type</label>
        <input type="text" name="type" id="type" value="" required />
    </div>
    <div>
        <label for="price">Price</label>
        <input type="text" name="price" id="price" value="" required />
    </div>
    <div>
        <label for="country">Country</label>

        <select name="country" id="country" required>
        <#list countries as val>
            <option value="${val.name}" <#if form.country == val.name>selected</#if> >${val.name}</option>
        </#list>
        </select>

    </div>
    <div>
        <label for="company">Company</label>
        <select name="company" id="company" required>
        <#list companies as val>
            <option value="${val.getInfo()}"
                    <#if form.company == val.getInfo()>selected</#if> >${val.getInfo()}</option>
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