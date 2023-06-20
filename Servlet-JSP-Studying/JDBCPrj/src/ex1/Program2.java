package ex1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title = "TEST3";
		String writerId = "DDC";
		String content = "hahaha3";
		String files = "";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO notice ("
				+ "    title,"
				+ "    writer_id,"
				+ "    content,"
				+ "    files"
				+ ") VALUES (?,?,?,?)";
		
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection con = DriverManager.getConnection(url,"newlec", "1234");
		 //Statement st = con.createStatement();
		 PreparedStatement st = con.prepareStatement(sql);
		 st.setString(1, title);
		 st.setString(2, writerId);
		 st.setString(3, content);
		 st.setString(4, files);
			
		 int result = st.executeUpdate();
	
		 System.out.println(result);
		 
		 st.close();
		 con.close();
	}
}
