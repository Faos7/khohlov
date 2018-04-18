<#-- @ftlvariable type="items" type="java.util.List<com.khohlov.khohlov.domain.model.Item>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>List of Items</title>
</head>
<body>
<nav role="navigation">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/item/create">Create a new item</a></li>
    </ul>
</nav>

<h1>List of Items</h1>

<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Type</th>
        <th>Prise</th>
        <th>Country</th>
        <th>Company Country</th>
        <th>Company name</th>
        <th>Company adres</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list items as item>
    <tr>
        <td>${item.name}</td>
        <td>${item.type}</td>
        <td>${item.price}</td>
        <td>${item.country.name}</td>
        <td>${item.company.country.name}</td>
        <td>${item.company.name}</td>
        <td>${item.company.adres}</td>
        <td><input type="button"  onclick="location.href='/item/remove/${item.itemId}'" value="Remove Item"></td>
    </tr>
    </#list>
    </tbody>
</table>
</body>
</html>