package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveEmployee
 */
public class SaveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
	
    public SaveEmployee() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "2611");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String department = request.getParameter("department");
		Integer salary = Integer.parseInt(request.getParameter("salary"));
		try {
			PrintWriter pw = response.getWriter();;
			PreparedStatement ps = con.prepareStatement("INSERT INTO details(id,name,age,department,salary) VALUES(?, ?, ?, ?, ?)");
			PreparedStatement ps2 = con.prepareStatement("SELECT * FROM details WHERE id = ?");
			ps2.setString(1, id);
			ResultSet rs = ps2.executeQuery();
			String duplicateId="";
			if(rs.next())
			{
				duplicateId = rs.getString("id");
			}
			if(!(id.equals(duplicateId)))
			{					
					ps.setString(1, id);
					ps.setString(2, name);
					ps.setInt(3, age);
					ps.setString(4, department);
					ps.setInt(5, salary);
					int success = ps.executeUpdate();
					if(success == 1)
					{
						pw.println("<html><body><h2>");
						pw.println("Employee Saved... </h2><a href='regemployee.html'>Save Another employee</a></body></html>");
					}
			}else{
				pw.println("<html><body><h2>");
				pw.println("Id Already taken please choose unique Id</h2>");
				pw.println("<h2>Employee Not Saved :)</h2></body></html>");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
