<!DOCTYPE html>
<html>
<head>
    <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript" language="javascript" src="apps.js"></script>
        </head>
<body>
<form id="calculator" action="inputservlet" method="post">
    <p>
        Calculate with POST
        <input name="left">
        <input name="right">
    </p>
    <p>Result: <span id="result">${sum}</span></p>
</form>

<form id="calculator2" action="inputservlet" method="get">
    <p>
        Calculate with GET
        <input name="left">
        <input name="right">
    </p>
    <p>Result: <span id="result2">${sum}</span></p>
</form>
</html>