import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connect {
	private static Connection dbTest;
	DB_Connect() {
		connectDB();
	}
	
	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", "database", "1234");
			System.out.println("데이터베이스에 연결되었습니다.");
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에 연결에 살패하였습니다.");
			System.out.println("SQLException : " + e);
		} catch(Exception e) {
			System.out.println("Exception : " + e);
		}
	}
	
	public static void main(String[] args) {
		new DB_Connect();
		try {
			dbTest.close();
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("SQLException : " + e);
		}
	}

}
