<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="DAO.DepartsDAO" %>
<%@ page import="DAO.DoctorDAO" %>
<%@ page import="bean.Doctor" %>
<%@ page import="bean.Departments" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.JsonParser" %>
<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>获得科室</title>
</head>
<body>
	<%
		ArrayList<Departments> departments;
		DepartsDAO departs = new DepartsDAO();
		departments=departs.getAllDepartsItem();//获得所有的科室
		String totaldeparts="";
		for(int i=0;i<departments.size()-1;i++){
			totaldeparts+=(departments.get(i).getDname()+",");	
		}
		System.out.println(totaldeparts);
		totaldeparts+=departments.get(departments.size()-1).getDname();
		PrintWriter pw=response.getWriter();
		pw.write(totaldeparts);
	 	pw.close();
	%>
</body>
</html>