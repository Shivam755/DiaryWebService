package DiaryMVC;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnector {
	static final String DB_URL = "jdbc:mysql://localhost:3306/diary?characterEncoding=latin1";
	static final String USER = "diaryUser";
	static final String PASS = "passwordWS";
	public static Connection makeConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection(DB_URL,USER,PASS);
			System.out.println("Connection Created");
		}catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}
}
