package hospital;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 登陆操作	
 */
	public class doLogin extends HttpServlet {

		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			String name=request.getParameter("name");
			System.out.print(name);
			String password = request.getParameter("password");
			System.out.println(password);
			String hint=null;//返回结果
			DBUtil db = new DBUtil();
			String sql="select * from user where name='"+name+"' and password='"+password+"'";
			ResultSet rs = db.query(sql);
		 	try {
				if(rs.next()){
					//说明匹配成功
					System.out.println("成功");
					hint="success";
					//request.getRequestDispatcher("stu_library.jsp").forward(request, response);
				}else{
					//StudentLogin.setLogined(false);
					//失败
					hint="fail";
					System.out.println("失败");
					//response.sendRedirect("login_failed.jsp");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 	PrintWriter pw=response.getWriter();
		 	pw.write(hint);
		 	pw.close();
				
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			doGet(request,response);
		}

}
