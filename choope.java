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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

public class choope extends HttpServlet {
	private static final long serialVersionUID = 1L;
       protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   response.setContentType("text/html");
			PrintWriter out=response.getWriter();
		
		String t1=request.getParameter("username");
			 
		try
			{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sydabi","sydabi","sydabi");
			CallableStatement cs=con.prepareCall("{call p_application(?,?)}");
			cs.setString(1, t1);
		cs.registerOutParameter(2, Types.INTEGER);
		
		cs.execute();
		
			out.print("<body bgcolor=orange>"+"<br>"+"<br>"+"<br>"+"<br>"+"<br>"+"<br>"+"<center>"+"<h2>"+"PERSON ID:"+cs.getInt(2)+"</h2>"+"</center>"+"</body>");
			out.print("<center>"+"<h2>"+"<a href=retrieved.jsp>"+"view your details here"+"</a>"+"<h2>"+"</center>");
			out.print("<h2>"+"YOU ARE ALLOWED TO VIEW THE DETAILS EXCEPT PASSWORD AND AADHAR NUMBER"+"</h2>");
		
		
		
		con.close();
			cs.close();
			
			}
			catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			}
	
			catch (Exception e) {
				
				e.printStackTrace();
				}
			}
       }
