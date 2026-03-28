<html>
<head>
    <title>catalog de resurse</title>
</head>
<body>
    <h1>resursele mele</h1>
    <ul>
        <#list resurse as doc>
            <li><b>${doc.nume}</b> - <a href="file:///${doc.caleFisier?replace('\\', '/')}">click aici sa deschizi fisierul</a></li>
        </#list>    </ul>
</body>
</html>