package com.mycompany.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaveBook {
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String a = "";
	PreparedStatement pstmt = null;

	public SaveBook() {

	}

	public SaveBook(String isbn, String title, String author) {
		try {
			String OracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String OracleUser = "hr";
			String OraclePasswd = "a1234";
			  String sql = "INSERT INTO REQUEST_BOOKLIST (request_keynum, isbn, title, author, user_id, request_state, request_text, request_date, ref_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			Date date = new Date();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = simpleDateFormat.format(date);
			java.sql.Date date1 = java.sql.Date.valueOf(formattedDate);

			con = DriverManager.getConnection(OracleUrl, OracleUser, OraclePasswd);
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 3); // 예시로 2을 넣었습니다.
			pstmt.setString(2, isbn);
			pstmt.setString(3, title);
			pstmt.setString(4, author);
			pstmt.setString(5, "user1234");
			pstmt.setString(6, "N");
			pstmt.setString(7, "This is a request for a Java programming book");
			pstmt.setDate(8, java.sql.Date.valueOf("2022-03-07"));
			pstmt.setDate(9, null); // ref_date는 null로 남겨둡니다.
			pstmt.executeUpdate();
			System.out.println("Data inserted successfully");

			pstmt.close();
			con.close();

		}  catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
