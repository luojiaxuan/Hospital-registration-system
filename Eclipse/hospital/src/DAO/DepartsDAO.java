package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import hospital.DBUtil;

import bean.*;
public class DepartsDAO {
	//获得所有的科室
	public ArrayList<Departments> getAllDepartsItem(){
		ArrayList<Departments> departs= new ArrayList<Departments>();
		DBUtil db = new DBUtil();//创建一个新的数据库实例
		String sql = "select * from department";
		ResultSet rs = db.query(sql);
		try {
			while(rs.next()) {
				Departments depart = new Departments();
				depart.setDid(rs.getString("did"));
				depart.setDname(rs.getString("dname"));
				departs.add(depart);
			}
			return departs;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
}
