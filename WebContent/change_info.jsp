<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会員情報変更</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<!--%@ page import="XXXX.Kaiin" %> -->
<!--% Kaiin user=(Kaiin)session.getAttribute("user"); %>-->
<body>

<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   <!--ヘッダー用テキスト<p><a href=".jsp">ボタン名</a></p>-->
<p align="right"><%-- <%=user.getKaiinname()%> --%>様</p>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<div id="main">

<h2>会員情報</h2>

<ul>
<li><u><a href="change_password.jsp">パスワード変更</a></u></li>
</ul>



</div>
</div>
</body>
</html>
