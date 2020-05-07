<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="java.util.*" import="dto.*"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head><% Kaiin user=(Kaiin)session.getAttribute("Kaiin"); %>
<body>
<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   
<p align="right"><%=user.getKaiinname()%>様</p>
<hr>
</header>
<h1>お気に入り銘柄一覧</h1>
<br>
<% List<Order> result= (List<Order>)request.getAttribute("meigaraList");%>


<table border="1">
<tr>
	<th>銘柄コード</th>
	<th>銘柄名</th>
	<th>市場</th>
	<th>業種</th>
	<th>株価</th>
	<th>　　</th>
</tr>
<% if(result==null){
	System.out.print(" ");
	
}else{

	  for (int i = 0; i < result.size(); i++) { 
		 for(Order o : result){ %>
		 <tr>
			<td><%=o.getMeigaracd()%></td>
			<td><%=o.getMeigaraname()%></td>
			<td><%=o.getMarketname()%></td>
			<td><%=o.getBusinessname()%></td>
			<td><%=o.getStockprice()%></td>
			<%-- <td><input type="hidden" name="searchcd" value="<%=o.getMeigaracd()%>"></td> --%>
			<td><a href="./meigara_detail?meigaracd=<%=o.getMeigaracd()%>">詳細</a></td>
			<td><a href="./love_delete?love=<%=o.getMeigaracd()%>">削除</a></td>
			<!-- <td><input type="submit" value="詳細"></td> -->
		
		</tr>
		<%
		 }
	  }
			}
	  
		%>
	</table>
	
	
	

</body>
</html>