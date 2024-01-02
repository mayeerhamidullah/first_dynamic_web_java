package nuvvuleka;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class loginservlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/sydabi","sydabi","sydabi");
			
			String t1=request.getParameter("username");
			
			
			String t2=request.getParameter("password");
			String t3=request.getParameter("fathername");
			String t4=request.getParameter("gender");
			String t5=request.getParameter("aadhar");
			
			CallableStatement ps=con.prepareCall("{call p_insert(?,?,?,?,?)}");
			
			ps.setString(1, t1);
			ps.setString(2, t2);
			ps.setString(3, t3);
			ps.setString(4, t4);
			ps.setString(5, t5);
			 ps.executeUpdate();
		if(ps!=null) {
				RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
				rd.forward(request,response);
			
		}
		
		else
		{
		RequestDispatcher rd=request.getRequestDispatcher("registerfail.jsp");
		rd.forward(request,response);
		}
			con.close();
			ps.close();
			
			
			
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
