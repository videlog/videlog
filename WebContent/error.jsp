<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String errorMessage = (String)request.getAttribute("errorMessage");
    if(errorMessage == null){
        errorMessage = "";
    }
    String errorTitle = (String)request.getAttribute("errorTitle");
    if(errorTitle == null){
        errorTitle = "";
    }
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="page">

<header>
<h1>長谷川ネット証券</h1>   <!--ヘッダー用テキスト-->
<hr>
</header>

<div id="main">
<div class="errortitle"><%= errorTitle %></div>
<div class="errormessage1"><%= errorMessage %></div>
<div style="text-align : center ;"><button type="button" align="center" onClick="location.href='./top.jsp'">トップページへ</div>

</body>
</html>
