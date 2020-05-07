<%@ page language="java" contentType="text/html; charset=UTF-8"
  import="dto.*"   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <%@ page import="XXXX.Kaiin" %> --%>
<%-- <% Kaiin user=(Kaiin)session.getAttribute("user"); %> --%>
<%-- <%@ page import="XXXX.Order" %> --%>
<% Order result=(Order)request.getAttribute("result"); %>


<title>Insert title here</title>
<link href="css/a.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">

function keisan(){
	// 設定開始

	// 注文数量
	console.log("keisann");
	var price1 = document.getElementById("ordernumber").value * <%=result.getUnit()%> ; // 
	console.log(price1);
	document.getElementById("field1").value = price1; // 小計を表示
}

function orderChange(){
if(document.getElementById('changeSelect')){
id = document.getElementById('changeSelect').value;
console.log("orderChange");
if(id == 'select1'){
//フォーム
console.log("select1");
console.log(document.getElementById('firstBox2'));
document.getElementById('firstBox2').disabled = true;
}else if(id == 'select2'){
//フォーム
	document.getElementById('firstBox2').disabled = false;
}
}
}
//オンロードさせ、リロード時に選択を保持
window.onload = orderChange;

</script> 

</head>

<body>
<div id="page">

<header>
<h1><a href="home.jsp">長谷川ネット証券</a></h1>   <!--ヘッダー用テキスト<p><a href=".jsp">ボタン名</a></p>-->
<p align="right"><%-- <%=user.getKaiinname()%> --%>様</p>
<hr>
</header>

<div id="main">
<!-- p align="right"><button type=“button” onclick="window.open(‘top.jsp’)“style=“background-color:最初の背景色;width:200px;height:28px;" 
onmouseover="this.style.background=‘マウスが触れた時の背景色‘"
onmouseout="this.style.background=‘マウスが離れた時の背景色’">ログアウト</button></p> -->
<h2>注文内容</h2>
<br>
<div style="padding: 10px; margin-bottom: 10px; margin-left:auto;margin-right:auto; border: 1px solid #333333; width: 400px;">
<ul>
<li>[ <%=result.getMeigaracd() %> ]</li>
<li><%=result.getMarketname()%></li>
<li><%=result.getBusinessname()%></li>
</ul>
<%=result.getMeigaraname()%>
<%=result.getStockprice()%>円
</div>
<br>
<div class="form_conf">
<form action="./order" method="post">
<table border="1">
<tr>
<th>注文数量</th>
<td><input style="text" name="ordernumber" id="ordernumber" onChange="keisan();">✕　単元<%=result.getUnit()%>株　= <input type="text" id="field1" name="field1" size="8" value="0">
</td>
</tr>
<tr>
<th>注文価格</th>
<td><select id="changeSelect" name="ordercondition" onchange="orderChange();">
<option value="select1">成行</option>
<option value="select2">指値</option>
</select>
<br>
<!-- 表示非表示切り替え -->
<div id="firstBox">
</div>
<!-- 表示非表示切り替え -->
<div id="secondBox">
<%-- <p><%=result.getYearhigh()%></p>
<p><%=result.getYearlow()%></p> --%>
<input id="firstBox2" name="sashine" type="text" />円
</div>
</td>
</tr>
<tr>
<th>執行条件</th>
<td><select name="executioncondition">
<option>条件なし</option>
<option>寄付</option>
<option>引け</option>
<option>指成</option>
</select>
</td>
</table>
<br>

    <input type="submit" name="order" value="注文する" id="submit_button">
</form>
<br>
<br>
    <%-- <form action="./meigara_detail" method="post">
   
    <input type="submit"  value="戻る">
     </form> --%>
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
