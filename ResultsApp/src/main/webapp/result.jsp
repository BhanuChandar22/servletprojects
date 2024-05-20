<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results App</title>
<style>
body{
		background-color: oldlace;
		width: 100%;
		height: 100%;
	}
	#main{
		width: 100%;
		height: 100%;
		
		display: flex;
		justify-content: center;
		align-items: center;
	}
	#center{
		height: 300px;
		margin: 40px;
		display: flex;
		justify-content: center;
		align-items: center;
		background-color: lightpink;
		box-shadow: rgba(50, 50, 93, 0.25) 0px 30px 60px -12px, rgba(0, 0, 0, 0.3) 0px 18px 36px -18px;
		padding: 20px;
	}
h1{
		text-align: center;
	}
</style>
</head>
<body>
	<div id="main">
		<div id="center">
			<% 
				dto.Result rs = (dto.Result)request.getAttribute("result");
				double marks = (double)request.getAttribute("marks");
			%>
			<div>
			<h1><u>Results</u></h1>
	<table border="1">
	 <tr>
	  	<th>HallTicket Number</th>
	  	<td><%= rs.getHtno() %></td>
	 </tr>
	 <tr>
	  	<th>Name</th>
	 	<td><%=rs.getName() %></td>	 
	 </tr>
	 <tr>
	  	<th>C</th>
	 	<td><%= rs.getC() %></td>
	 </tr> 	
	 <tr>
	  	<th>C++</th>
	 	<td><%= rs.getCpp() %></td>
	 </tr>
	 <tr>
	  	<th>Python</th>
	 	<td><%= rs.getPython() %></td>
	 </tr>
	 <tr>
	  	<th>Java</th>
	 	<td><%= rs.getJava() %></td>
	 </tr> 
	 <tr>
	 	<th>Total in Percentage</th>
	 	<td><%= marks %></td>
	 </tr>
	</table>
			</div>
		</div>
	</div>
</body>
</html>