<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>解析结果查看页</title>
    <style>
        table { width: 85%; min-height: 25px; line-height: 25px; text-align: center; border-color:#b6ff00; border-collapse: collapse;}
    </style>
</head>
<body>
<table border="1" align="center" >
    <tr>
        <td colspan="2">解析过程</td>
    </tr>
    <c:forEach items="${infoList}" var="info">
        <tr><td colspan="2">******************************解析开始******************************</td> </tr>
        <c:forEach items="${info}" var="m">
            <tr>
                <td>${m.key}</td><td>${m.value }</td>
            </tr>
        </c:forEach>
        <tr><td colspan="2">******************************解析结束******************************</td> </tr>
    </c:forEach>
</table>
</body>
</html>
