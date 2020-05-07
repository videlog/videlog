<%@ page language="java" contentType="text/html; charset=UTF-8"
import="java.util.*" import="dto.*"    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%@ page import="XXXX.Kaiin" %> --%>
<% Kaiin user=(Kaiin)session.getAttribute("Kaiin"); %>
<%-- <%@ page import="XXXX.Order" %> --%>
<% Order result=(Order)request.getAttribute("order"); %>
<title>Insert title here</title>
<link href="css/a.css" rel="stylesheet" type="text/css" media="screen" />
</head>

<body>
<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   <!--ヘッダー用テキスト<p><a href=".jsp">ボタン名</a></p>-->
<p align="right"><%=user.getKaiinname()%>様</p>
<hr>
</header>

<div id="main">
<!-- p align="right"><button type=“button” onclick=“window.open(‘top.jsp’)“style=“background-color:最初の背景色;width:200px;height:28px;”
onmouseover=“this.style.background=‘マウスが触れた時の背景色‘”
onmouseout=“this.style.background=‘マウスが離れた時の背景色’“>ログアウト</button></p> -->
<h2>注文内容確認</h2>
<br>
<div style="padding: 10px; margin-bottom: 10px; margin-left:auto;margin-right:auto; border: 1px solid #333333; width: 400px;">
<ul>
<li>[ <%=result.getMeigaracd() %> ]</li>
<li><%=result.getMarketcd()%></li>
<li><%=result.getBusinesscd()%></li>
</ul>
<%=result.getMeigaraname()%>
<%=result.getStockprice()%>
</div>
<br>
<div class="form_conf">
<form action="./order_2" method="post">
<table border="1">
<tr>
<th>注文数量</th>
<td><p><%=result.getOrdernumber() %> (単元<%=result.getUnit() %>株)</p>
</td>
</tr>
<tr>
<th>注文総額</th>
<td><p><%=result.getOrderprice()%> </p></td>
</tr>

<tr>
<th>執行条件</th>
<td><%=result.getExecutioncondition() %></td>
</tr>
</table>
<br>
    <input type="submit" name="order2" value="注文する" id="submit_button">
</form>
<br>
    <form action="order.jsp" method="post">
     <input type="submit" name="back" value="修正する" id="submit_button">
     </form>
</div>
<!--  <style type=”text/css”>
.form_conf {
    text-align: center;
}
.form_conf form {
    display: inline-block;
    margin: 0 10px;
}

</style>
-->
</div>
</div>
</body>
</html>
