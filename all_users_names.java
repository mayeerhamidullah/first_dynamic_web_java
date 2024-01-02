package nuvvuleka;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class all_users_names
 */
public class all_users_names extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sydabi","sydabi","sydabi");
			String t1=request.getParameter("male");
			String t2=request.getParameter("female");
			PreparedStatement ps=con.prepareStatement("select count(case when gender='male' then 1 end) malecount,count(case when gender='female' then 1 end) femalecount,count(case when gender='female' or gender='male' then 2 end) totalcount from member_registration  where gender=? or gender=?");
			ps.setString(1, t1);
			ps.setString(2, t2);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				out.print("<body bgcolor=paleblue>");
				out.print("<br>"+"<br>"+"<br>"+"<br>"+"<br>");
				out.print("<center>");
				int count=rs.getInt("malecount");
				out.print("<h2>"+"MALECOUNT:"+" "+count+"<br>"+"<br>");
				int  countone=rs.getInt("femalecount");
				out.print("<h2>"+"FEMALECOUNT:"+" "+countone+"<br>"+"<br>");
				int counttwo=rs.getInt("totalcount");
	            out.print("<h2>"+"TOTALCOUNT:"+" "+counttwo);
	            out.print("</center>");
	            out.print("</body>");
			}
			 else
			 {
				 out.print("something problem");
			 }
			 con.close();
				ps.close();
				rs.close();
			}
		 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
