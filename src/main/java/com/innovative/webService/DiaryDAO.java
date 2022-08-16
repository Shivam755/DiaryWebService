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
	
	public static List<DiaryEntry> getAllEntries(){
		List<DiaryEntry> list = new ArrayList<DiaryEntry>();
		try {
			Connection con = DBConnector.makeConnection();
			PreparedStatement ps = con.prepareStatement("select * from diaryEntry");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DiaryEntry de = new DiaryEntry(0, null, null, null);
				de.setId(rs.getInt(1));
				de.setTitle(rs.getString(2));
				de.setDate(rs.getString(3));
				de.setContent(rs.getString(4));
				list.add(de);
				System.out.println(list);
			}
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}




















