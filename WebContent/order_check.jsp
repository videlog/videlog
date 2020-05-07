<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*" import="dto.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>




<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
</head><% Kaiin user=(Kaiin)session.getAttribute("Kaiin");%>
<body>
<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>                                                                                      
<h4 align="right"><%= user.getKaiinname()%>様</h4>
<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>
<h2 align="center" style="font-size:24px">注文状況一覧</h2>
<br>
<% List<Order> result= (List<Order>)request.getAttribute("result");

if(result==null){
	System.out.print(" ");
}else{
%>
<table border="1">
<tr>
	<th>受付番号</th>
	<th>銘柄コード</th>
	<th>銘柄名</th>
	<th>注文数</th>
	<th>注文金額</th>
	<th>注文日</th>
	<th>注文種類</th>
	<th>ステータス</th>
	<th>評価額</th>
	<th>     </th>
</tr>

	 <% for (int i = 0; i < result.size(); i++) {
		 for(Order o : result){ %>
		 <tr>
			<td><%=o.getOrdercd()%></td>
			<td><%=o.getMeigaracd()%></td>
			<td><%=o.getMeigaraname()%></td>
			<td><%=o.getOrdernumber()%></td>
			<td><%=o.getOrderprice()%></td>
			<td><%=o.getOrderdate()%></td>
			<td><%=o.getOrdertype()%></td>
			<td><%=o.getOrderstatus()%></td>
			<td> - </td>
			<%-- <td><input type="hidden" name="searchcd" value="<%=o.getOrdercd()%>"></td> --%>
			<form action="history_detail" action="get">
			<td><a href="./history_detail?ordercd=<%=o.getOrdercd()%>">詳細</a></td>
			</form>
			<!-- <td><input type="submit" value="詳細"></td> -->
		
		</tr>
		<% }
			}
	 /* } */
}
		%>
	</table>

</body>
</html>
