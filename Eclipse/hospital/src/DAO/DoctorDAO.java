package DAO;
import hospital.DBUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Doctor;

public class DoctorDAO {
//	public static void main(String args[]) {
//		DoctorDAO d = new DoctorDAO();
//		d.getDoctorListByDname("骨科");
//	}
	//获得Doctor数据
	public ArrayList<Doctor> getDoctorListByDname(String Dname){
		ArrayList<Doctor> DoctorLists= new ArrayList<Doctor>();
		DBUtil db=new DBUtil();
		Dname=Dname.trim();
		String sql="select * from doctor where dname ='"+Dname+"'";
		System.out.println(sql);
		ResultSet rs=db.query(sql);//执行查询
		
		try {
			//System.out.println("至少进入try了");
			while(rs.next()) {
				System.out.println(rs.getString("dname"));
				Doctor doc = new Doctor();
				doc.setId(rs.getString("id"));
				doc.setDid(rs.getString("did"));
				doc.setDname(rs.getString("dname"));
				doc.setName(rs.getString("name"));
				doc.setAgenda(rs.getString("agenda"));//获得医生日程表
				doc.setIntro(rs.getString("intro"));//获得医生基本介绍
				DoctorLists.add(doc);
			}
			return DoctorLists;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
}
