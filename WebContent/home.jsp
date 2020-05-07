<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*" import="dto.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホーム</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head><% Kaiin user=(Kaiin)session.getAttribute("Kaiin"); %>
<body>
<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   
<p align="right"><%=user.getKaiinname()%>様</p>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>


<div id="main" style="padding-top: 70px">
<ul>
<p><a href="search.jsp" id="menu">銘柄検索</a></p>
<!-- <form action="history_listout" method="get"> -->
<p><a href="history_listout?usercd=<%=user.getKaiincd()%>" id ="menu">注文状況一覧</a></p>
<!-- </form> -->
<p><a href="love_listout?usercd=<%=user.getKaiincd()%>" id ="menu">お気に入り銘柄一覧</a></p>
<p><a href="change_password.jsp" id ="menu">会員情報の変更</a></p>
</ul>
</div>
</div>
</body>
</html>
