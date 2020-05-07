<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="java.util.*" import="dto.*"  pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>search</title>
<link href="a.css" rel="stylesheet" type="text/css" media="screen" />
 <!-- <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>  -->
</head>
<body>
<header>
<%--HttpSession session=req.getSession(); --%> <%--@ page import="XXXXX.Kaiin" --%>
<%--Kaiin user=(Kaiin)session.getAttribute("user"); --%>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>                                                                                        
<h4 align="right"><%--= user.getKaiinname()--%>様</h4>

<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>

<h3 align="center" style="font-size:24px" >銘柄検索</h3><br>
<%-- <div class="errormessage"><%= request.getAttribute("errorMessage") %></div> --%>
<form action="meigara_listout" method="post">

<pre>
                                  銘柄コード <input type="text" size="20" name="meigara_cd">              銘柄名<input type="text" size="20" name="meigara_name"><input type="submit" value="検索する">
</pre>
</form>
<hr>
<form action="meigara_detail" method="get">

<%
List<Order> result= (List<Order>)request.getAttribute("result");
if(result==null){
	System.out.print(" ");
}else{
%>
<a align="left">該当する銘柄は<%=result.size() %>件です。</a>
<table border="1">
<tr>
	<th>銘柄コード</th>
	<th>銘柄名</th>
	<th>市場</th>
	<th>業種</th>
	<th>株価</th>
	<th>　　</th>
</tr>

	 <% 
		 for(Order o : result){ %>
		 <tr>
			<td><%=o.getMeigaracd()%></td>
			<td><%=o.getMeigaraname()%></td>
			<td><%=o.getMarketname()%></td>
			<td><%=o.getBusinessname()%></td>
			<td><%=o.getStockprice()%></td>
			<%-- <td><input type="hidden" name="searchcd" value="<%=o.getMeigaracd()%>"></td> --%>
			<td><a href="./meigara_detail?meigaracd=<%=o.getMeigaracd()%>">詳細</a></td>
			<!-- <td><input type="submit" value="詳細"></td> -->
		
		</tr>
		<% 
			}
	 /* } */
}
		%>
	</table>
	</form>
		 
	


</form> 

<!-- <label>銘柄検索 </label>   
            <label>
                <input type="radio" name="choice" value="meigara_cd" checked='checked' /> 銘柄コード
            </label>
            <label >
                <input type="radio" name="choice" value="meigara_name" /> 銘柄名
            </label>
            -->





<!-- <div class="container">
	<div class="row">
<br /><br /> -->
<!-- <form action="meigara_listout" method="post" class="form-horizontal"> -->
    <!-- <div class="form-group">
        <div class="col-xs-9"> -->
           <!--  <input type="text" class="form-control" name="company"
                   required data-fv-notempty-message="The company name is required" /> <br />
                   
            <label class="radio-inline">銘柄検索 </label>  
            <label class="radio-inline">
                <input type="radio" name="category" value="meigara_cd" checked='checked' /> 銘柄コード
            </label>
            <label class="radio-inline">
                <input type="radio" name="category" value="meigara_name" /> 銘柄名
            </label>
            
            
    
                  
        </div>
     
        <div class="col-xs-2">
            <input type="submit" class="btn btn-success" value="検索する">
        </div>
    </div>
</form>




	</div>
</div>
 -->

<!-- <div id="page">


<div class="container">
	<div class="row">
<br /><br />
<form id="togglingForm" method="post" class="form-horizontal">
<div class="form-group">
        <div class="col-xs-9">
                   
            <label class="radio-inline">銘柄検索 </label>  
            <label class="radio-inline">
                <input type="radio" name="category" value="meigara_cd" checked='checked' /> 銘柄コード
            </label>
            <label class="radio-inline">
                <input type="radio" name="category" value="meigara_name" /> 銘柄名
            </label>
            
            
    
                  
        </div>
     
        <div class="col-xs-2">
            <button type="button" class="btn btn-success" data-toggle="#jobInfo">検索する</button>
        </div>
    </div>
</form>




	</div>
</div>
 -->




<!-- <div id="main">

<pre>
<align="left">銘柄コードから検索<input type="text" name="meigara_cd">                <align="right">銘柄名から検索<input type="text" name="meigara_cd"><input type="submit" value="検索">
</pre>




</div>
</div> -->
</body>




</html>
