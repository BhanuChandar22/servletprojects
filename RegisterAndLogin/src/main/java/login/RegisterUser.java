package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterUser
 */
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUser() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","2611");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		PrintWriter pw = response.getWriter();
		PreparedStatement ps;
		String dupemail="";
		try {
			ps = con.prepareStatement("SELECT * FROM users WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				dupemail = rs.getString(4);
			}
			ps = con.prepareStatement("INSERT INTO users(name,contact,email,address,password) values(?,?,?,?,?)");
			if(!(email.equals(dupemail)))
			{
				ps.setString(1, name);
				ps.setString(2, contact);
				ps.setString(3, email);
				ps.setString(4, address);
				ps.setString(5, password);
				int success = ps.executeUpdate();
				if(success == 1)
				{
					pw.println("<html><body>");
					pw.println("<h2>Registration successful!</h2>");
					pw.println("<a href='regform.html'>Back</a>");
					pw.println("</body></html>");
				}
			}else {
				pw.println("<html><body>");
				pw.println("<h2>Registration failed! Please enter unique email id</h2>");
				pw.println("<a href='regform.html'>Back</a>");
				pw.println("</body></html>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		super.destroy();
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
