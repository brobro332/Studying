package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		NoticeService service = new NoticeService();
		Notice notice = service.getNotice(id);
		request.setAttribute("n", notice);

		/*
		 * String url = "jdbc:oracle:thin:@localhost:1521/xepdb1"; String sql =
		 * "SELECT * FROM NOTICE WHERE ID=?";
		 * 
		 * try { Class.forName("oracle.jdbc.driver.OracleDriver"); Connection con =
		 * DriverManager.getConnection(url,"newlec", "1234"); PreparedStatement st =
		 * con.prepareStatement(sql); st.setInt(1, id);
		 * 
		 * ResultSet rs = st.executeQuery();
		 * 
		 * rs.next();
		 * 
		 * String title = rs.getString("TITLE"); Date regDate = rs.getDate("REGDATE");
		 * String writerId = rs.getString("WRITER_ID"); String hit =
		 * rs.getString("HIT"); String files = rs.getString("FILES"); String content =
		 * rs.getString("CONTENT");
		 * 
		 * Notice notice = new Notice( id, title, regDate, writerId, hit, files, content
		 * );
		 * 
		 * request.setAttribute("n", notice);
		 * 
		 * 
		 * 
		 * rs.close(); st.close(); con.close(); } catch (ClassNotFoundException e) {
		 * e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); }
		 */
		
		//forward 
		request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp").forward(request, response);
	}
}
