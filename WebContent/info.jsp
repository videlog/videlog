<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*" import="dto.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- <%HttpSession session=request.getSession();%> <%@ page import="XXXX.Kaiin" %>
<% Kaiin user=(Kaiin)session.getAttribute("user"); %> --%>
<% Order result=(Order)request.getAttribute("result"); %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head><% Kaiin user=(Kaiin)session.getAttribute("Kaiin"); %>
<body>
<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>                                                                                        
<h4 align="right"><%=user.getKaiinname()%>様</h4>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<br>

<form action="./love_add" method="post">

<input type="hidden" name="love" value="<%=result.getMeigaracd()%>">
<input type="submit" value="お気に入り"><!-- onClick="alert('お気に入り銘柄に登録しました！');" -->
</form>
<%String message=(String)request.getAttribute("message"); 
if(message==null){
message="  ";}%>
<h3><%=message%></h3>
<ul>
<li>[ <%=result.getMeigaracd() %> ]</li>
<li><%=result.getMarketname()%></li>
<li><%=result.getBusinessname()%></li>
</ul>
<%=result.getMeigaraname()%>
<%=result.getStockprice()%>円
</div>
</form>
<br>
<table border="1" align="left">
<tr>
<th>始値（当日）</th>
<td><%=result.getStockprice()%></td>
</tr>
<tr>
<th>高値（当日）</th>
<td><%=result.getHighprice()%></td>
</tr><tr>
<th>安値（当日）</th>
<td><%=result.getLowprice()%></td>
</tr><tr>
<th>売り気配値</th>
<td><%=result.getUrivalue()%></td>
</tr><tr>
<th>買い気配値</th>
<td><%=result.getKaivalue()%></td>
</tr><tr>
<th>高値（年初来）</th>
<td><%=result.getYearhigh()%></td>
</tr><tr>
<th>安値（年初来）</th>
<td><%=result.getYearlow()%></td>
</tr>
</table>
<img scr="Image File.jpg" align="center" border="1"></img><br>

<form action="./to_order" method="post">
<input type="hidden" name="order" value="<%=result.getMeigaracd()%>">
<h3><input type="submit"align="right" value="注文する"></h3>
</form>

</body>
</html>
