package com.innovative.webService;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DiaryDAO {

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
}
