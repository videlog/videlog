<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String errorMessage = (String)request.getAttribute("errorMessage");
    if(errorMessage == null){
        errorMessage = "";
    }
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<!--%@ page import="XXXX.Kaiin" %> -->
<!--% Kaiin user=(Kaiin)session.getAttribute("user"); %>-->
<body>

<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   <!--ヘッダー用テキスト<p><a href=".jsp">ボタン名</a></p>-->
<p align="right"><%-- <%=user.getKaiinname() %> --%>様</p>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<div id="main">

<h2>パスワード変更</h2>
<br>
<div class="errormessage"><%= errorMessage %></div>
<form action="./password_change" method="POST">
<div style="padding: 10px; margin-bottom: 10px;
margin-left:auto;margin-right:auto; border: 1px solid #333333; width: 500px;">
<table>
  <tr>
    <td>
      現在のログインパスワード
    </td>
    <td>
      <input type="password" name="old_password" >
    </td>
  </tr>
  <tr>
    <td>
      変更後のログインパスワード
    </td>
    <td>
      <input type="password" name="new_password">
    </td>
  </tr>
  <tr>
    <td>
      変更後のログインパスワード(確認用)
    </td>
    <td>
      <input type="password" name="new_password_2">
    </td>
  </tr>
</table>
</div>
<br>
<div style="text-align : center ;">
<input type="submit" name="change_password" value="変更" id="submit_button" align="center">
</div>
</form>


</div>
</div>
</body>
</html>
