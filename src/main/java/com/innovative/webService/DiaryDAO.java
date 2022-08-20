package com.innovative.webService;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {

	public int delete(int Id) {
		int status = 0;
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("delete from diaryEntry where id=?");
			ps.setInt(1, Id);
			status = ps.executeUpdate();
			System.out.println("Records Deleted!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int insert(DiaryEntry de) {
		int status = 0;
		try {
			Date date = new Date();
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			Connection con = DBConnector.makeConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM diaryEntry ORDER BY ID DESC LIMIT 1");
			ResultSet rs = stmt.executeQuery();
			rs.next();
			int currentid = rs.getInt(1);
			PreparedStatement ps = con
					.prepareStatement("insert into diaryEntry(Id,heading,content,dateOfCreation) values(?,?,?,?)");
			ps.setInt(1, currentid + 1);
			ps.setString(2, de.getTitle());
			ps.setString(3, de.getContent());
			ps.setString(4, ft.format(date));
			status = ps.executeUpdate();
			System.out.println("Records Added!");
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return status;
	}

	public static int update(DiaryEntry de) {
		int status = 0;
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("update diaryEntry set heading=?, content=? where id=?");
			ps.setString(1, de.getTitle());
			ps.setString(2, de.getContent());
			ps.setInt(3, de.getId());
			status = ps.executeUpdate();
			System.out.println("Records Updated!");
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static List<DiaryEntry> getAllEntries() {
		List<DiaryEntry> list = new ArrayList<DiaryEntry>();
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("select * from diaryEntry");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DiaryEntry de = new DiaryEntry();
				de.setId(rs.getInt(1));
				de.setTitle(rs.getString(2));
				de.setDate(rs.getString(4));
				de.setContent(rs.getString(3));
				list.add(de);
				System.out.println(list);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static DiaryEntry getEntryByID(int Id) {
		DiaryEntry de = new DiaryEntry();
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("select * from diaryEntry where id = ?");
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			de.setId(rs.getInt(1));
			de.setTitle(rs.getString(2));
			de.setDate(rs.getString(4));
			de.setContent(rs.getString(3));
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return de;
	}
	
	public static DiaryEntry getEntryByTitle(String title) {
		DiaryEntry de = new DiaryEntry();
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("select * from diaryEntry where heading = ?");
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			rs.next();
			de.setId(rs.getInt(1));
			de.setTitle(rs.getString(2));
			de.setDate(rs.getString(4));
			de.setContent(rs.getString(3));
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return de;
	}
}
