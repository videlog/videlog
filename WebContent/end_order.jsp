<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="java.util.*" import="dto.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注文完了</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<%-- <!--<%@ page import="XXXX.Kaiin" %> --> --%>
<!--% Kaiin user=(Kaiin)session.getAttribute("user"); %>-->
<% Order result=(Order)request.getAttribute("order"); %>
<body>

<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   <!--ヘッダー用テキスト<p><a href=".jsp">ボタン名</a></p>-->
<p align="right"><%-- <%=user.getKaiinname()%> --%>様</p>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<div id="main">

<div id="complete">
<table border="1" align="center">
<tr>
<td>
会員情報の変更が完了しました。
</td>
<tr>
<td>
受付番号は、<%= result.getOrdercd() %>です。
</td>
</tr>
</table>
</div>
<br>
<div style="text-align: center">
<p><a href="home.jsp">ホームへ戻る</a></p>
</div>
</div>
</div>
</body>
</html>
