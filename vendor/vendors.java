package vendor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VendorRegistration
 */
@WebServlet("/register")
public class vendors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String Vendorname = request.getParameter("name");
		String Vendoremail = request.getParameter("email");
		String Vendorpassword = request.getParameter("pass");
		String Vendormobile = request.getParameter("contact");
		RequestDispatcher dispatcher=null;
		Connection con= null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3308/vendorsdb","root","");
			PreparedStatement pst=con.prepareStatement("insert into vendorregister(name,email,password,contact) values(?,?,?,?)");
			pst.setString(1, Vendorname);
			pst.setString(2, Vendoremail);
			pst.setString(3, Vendorpassword);
			pst.setString(4, Vendormobile);
			
			int rowcount =pst.executeUpdate();
			dispatcher= request.getRequestDispatcher("login.jsp");
			
			if(rowcount>0)
			{
				request.setAttribute("status", "success");
				
			}
			else {
				request.setAttribute("status", "failed");
			}
			dispatcher.forward(request,response);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		try
		{
			con.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();			}
		
	
		
	}

}
