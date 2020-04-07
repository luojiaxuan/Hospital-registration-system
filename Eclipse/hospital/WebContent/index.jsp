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
<title>index</title>
</head>
<body>
	<%
	//String dname="骨科";
	String dname=request.getParameter("dname");//获取科室名称
	//ArrayList<Departments> departments;
	//DepartsDAO departs = new DepartsDAO();
	//departments=departs.getAllDepartsItem();//获得所有的科室
	ArrayList<Doctor> DoctorList;
	DoctorDAO Doctors = new DoctorDAO();
	DoctorList=Doctors.getDoctorListByDname(dname);
	Gson gson = new Gson();
	String str = gson.toJson(DoctorList);
	System.out.println(str);
	PrintWriter pw=response.getWriter();
	pw.write(str);
 	pw.close();
	%>
	
</body>
</html>