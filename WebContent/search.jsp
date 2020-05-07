<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*" import="dto.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%-- <%
    String errorMessage = request.getAttribute("errorMessage");
    if(errorMessage == null){
        errorMessage = "";
    }
%> --%>
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
<%Kaiin user=(Kaiin)session.getAttribute("user");%>
<h1><a href="home.jsp" >長谷川ネット証券</a></h1>                                                                                       
<h4 align="right"><%= user.getKaiinname()%>様</h4>

<hr>
</header>
<div style="text-align : right ;"><button type="button" align="center" onClick="location.href='./logout'">ログアウト</div>

<h3 align="center" style="font-size:24px" >銘柄検索</h3><br>
<%-- <div class="errormessage"><%= request.getAttribute("errorMessage") %></div> --%>
<form action="meigara_listout" method="post">

<pre>
                                  銘柄コード <input type="text" size="20" name="meigara_cd">              銘柄名<input type="text" size="20" name="meigara_name"><input type="submit" value="検索する">
</pre>

<!-- <label>銘柄検索 </label>   
            <label>
                <input type="radio" name="choice" value="meigara_cd" checked='checked' /> 銘柄コード
            </label>
            <label >
                <input type="radio" name="choice" value="meigara_name" /> 銘柄名
            </label>
            -->

<%-- <jsp:include page="list_to_include.jsp"></jsp:include> --%>
</form>




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
