<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="hospital.DBUtil" %>
    <%@ page import="java.io.IOException" %>
    <%@ page import="java.io.PrintWriter" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
</head>
<body>
	<%
	String name=request.getParameter("name");
	String password=request.getParameter("password");
	DBUtil db = new DBUtil();
	String sql="insert into user values ('"+name+"','"+password+"')";
	db.update(sql);
	String hint="success";
	//插入完成
	PrintWriter pw=response.getWriter();
		 	pw.write(hint);
		 	pw.close();
	%>
</body>
</html>