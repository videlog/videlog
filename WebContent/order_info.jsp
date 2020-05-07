<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*" import="dto.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<%Order result=(Order)request.getAttribute("result"); %>
<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>                                                                                       
<h4 align="right"><%--= user.getKaiinname()--%>様</h4>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<h2>注文詳細</h2>
<br>
<table border="1" align="left">
<tr>
<th>受付番号</th>
<td><%=result.getOrdercd()%></td>
</tr>
</table>
<br>
<br>
<br>
<br>
<table border="1" align="left">
<tr>
<th>銘柄コード</th>
<td><%=result.getMeigaracd()%></td>
<form action="meigara_detail" action="get">
<td><a href="./meigara_detail?meigaracd=<%=result.getMeigaracd()%>">銘柄詳細</a></td>
</form>
</tr>
<tr>
<th>銘柄名</th>
<td><%=result.getMeigaraname()%></td>
<th>注文日</th>
<td><%=result.getOrderdate()%></td>
</tr>
<tr>
<th>市場</th>
<td><%=result.getMarketname()%></td>
<th>注文種類</th>
<td><%=result.getOrdertype()%></td>
</tr><tr>
<th>業種</th>
<td><%=result.getBusinessname()%></td>
<th>注文数</th>
<td><%=result.getOrdernumber()%></td>
</tr><tr>
<th>評価額</th>
<td>---</td>
<th>注文金額</th>
<td><%=result.getOrderprice()%></td>
</tr><tr>
<th>損益</th>
<td>--</td>
<th>注文条件</th>
<td><%=result.getOrdercondition()%></td>
</tr><tr>
<th>ステータス</th>
<td><%=result.getOrderstatus()%></td>
<th>執行条件</th>
<td><%=result.getExecutioncondition()%></td>
</tr>
</table>
<br>
<br>

<p><a href="./history_listout<%-- $kaiincd=<%=user.getKaiincd()%> --%>">戻る</a>


</body>
</html>
