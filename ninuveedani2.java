package nuvvuleka;

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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;


public class ninuveedani2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			int t1=0;
			 t1=Integer.parseInt(request.getParameter("s_no"));
			
			try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sydabi","sydabi","sydabi");
			CallableStatement cs=con.prepareCall("{call p_retrieve_all_details(?,?,?,?)}");
			cs.setInt(1, t1);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
			out.print("<body bgcolor=skyblue>");
			out.print("<center>");
			out.print("<br>"+"<br>"+"<br>"+"<br>");
	out.print("USERNAME:"+" "+cs.getString(2)+"<br>"+"<br>");
	out.print("FATHERNAME:"+" "+cs.getString(3)+"<br>"+"<br>");
	out.print("GENDER:"+" "+cs.getString(4)+"<br>"+"<br>");
	out.print("<a href=gendercount.jsp>"+"<h2>"+"PLEASE CLICK HERE TO VIEW STATISTICS"+"</h2>"+"</a>");
	
	out.print("</center>");
	out.print("</body>");
	con.close();
			cs.close();
			con.close();
			}
			catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			}
	
			catch (Exception e) {
				
				e.printStackTrace();
				}
	}
	
}
	
	
	
	
	



