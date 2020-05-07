<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC “-//W3C//DTD HTML 4.01 Transitional//EN” “http://www.w3.org/TR/html4/loose.dtd“>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if(errorMessage == null){
        errorMessage = "";
    }
%>
<html>
<head>
<meta http-equiv=“Content-Type” content=“text/html; charset=UTF-8">
<title>長谷川ネット証券｜トレーディングシステム</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id=“page”>

<header>
<h1>長谷川ネット証券</h1>   <!--ヘッダー用テキスト-->
<hr>
</header>
<br>
<br>
<div id=“main”>
<div class="errormessage"><%= errorMessage %></div>
<form action="login" method="post">

<div style="padding: 10px; margin-bottom: 10px; margin-left:auto;margin-right:auto; border: 1px solid #333333; width: 400px;">
<p align="center">ユーザーID <input type="text" name="kaiincd"></p>
<br>
<p align="center">パスワード <input type="password" name="password"></p>
<br>
</div> 
<div style="text-align : center;"><input type="submit" name="login" value="ログイン" id="submit_button" align="center"></div>
</form>
</div>
</div>
</body>
</html>
