<#-- @ftlvariable type="countries" type="java.util.List<com.khohlov.khohlov.domain.model.Country>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Countries</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/country/create">Create a new country</a></li>
    </ul>
</nav>

<h1>List of Countries</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list countries as country>
    <tr>
        <td>${country.name}</td>
        <td><input type="button"  onclick="location.href='/country/remove/${country.id}'" value="Remove Country"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>