
package nuvvuleka;
import java.io.IOException;
import java.io.PrintWriter;
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


public class loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sydabi","sydabi","sydabi");
			String t1=request.getParameter("username");
			String t2=request.getParameter("password");
			PreparedStatement ps=con.prepareStatement("select * from member_registration  where username=? and password=?");
			ps.setString(1, t1);
			ps.setString(2, t2);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				RequestDispatcher rd=request.getRequestDispatcher("retrie.jsp");
				rd.forward(request,response);
			}
			else
			{
				out.print("<body bgcolor=palered>");
				out.print("<center>");
				out.print("<br>"+"<br>"+"<br>"+"<br>"+"<br>"+"<h2>"+"LOGIN FAILED"+"</h2>"+"<br>");
				out.print("<h2>"+"USERID OR PASSWORD IS INCORRECT"+"</h2>");
				out.print("</center>");
				out.print("</body>");
			}
		con.close();
		ps.close();
		rs.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
