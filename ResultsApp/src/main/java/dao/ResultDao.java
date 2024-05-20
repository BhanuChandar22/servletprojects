package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Result;

public class ResultDao {
	
	private String query = "SELECT * FROM marks WHERE htno=?";
	int c;
	int cpp;
	int python;
	int java;
	public double totalMarks()
	{
		int marks = c + cpp + python + java;
		double Percentage = ((double)marks/400)*100;
		return Percentage;
	}
	public Result getMarks(String htno)
	{
		Result r=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/resultapp", "root", "2611");
			PreparedStatement ps =con.prepareStatement(query);
			ps.setString(1, htno);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				c = rs.getInt(3);
				cpp = rs.getInt(4);
				python = rs.getInt(5);
				java = rs.getInt(6);
				r = new Result();
				r.setHtno(rs.getString(1));
				r.setName(rs.getString(2));
				r.setC(c);
				r.setCpp(cpp);
				r.setPython(python);
				r.setJava(java);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
}
