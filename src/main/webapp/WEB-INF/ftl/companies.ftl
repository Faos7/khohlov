<#-- @ftlvariable type="companies" type="java.util.List<com.khohlov.khohlov.domain.model.Stock>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Companies</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/company/create">Create a new company</a></li>
    </ul>
</nav>

<h1>List of Companies</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Adres</th>
        <th>Phone</th>
        <th>Site</th>
        <th>Country</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list companies as company>
    <tr>
        <td>${company.name}</td>
        <td>${company.adres}</td>
        <td>${company.phone}</td>
        <td>${company.site}</td>
        <td>${company.country.name}</td>
        <td><input type="button"  onclick="location.href='/company/remove/${company.companyId}'" value="Remove Company"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>