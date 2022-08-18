package com.innovative.webService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiaryDAO {
	
//	public 

	public int delete(int Id){
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
	
	public static int update(DiaryEntry de) {
		int status =0 ;
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("update diaryEntry set heading=?, content=? where id=?");
			ps.setString(1, de.getTitle());
			ps.setString(2, de.getContent());
			ps.setInt(3, de.getId());
			status = ps.executeUpdate();
			
			System.out.println("Records Updated!");
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static List<DiaryEntry> getAllEntries(){
		List<DiaryEntry> list = new ArrayList<DiaryEntry>();
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("select * from diaryEntry");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DiaryEntry de = new DiaryEntry();
				de.setId(rs.getInt(1));
				de.setTitle(rs.getString(2));
				de.setDate(rs.getString(4));
				de.setContent(rs.getString(3));
				list.add(de);
				System.out.println(list);
			}
			con.close();
		} catch(Exception e) {
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
		} catch(Exception e) {
			e.printStackTrace();
		}
		return de;
	}
}




















