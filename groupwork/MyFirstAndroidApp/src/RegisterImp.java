import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class RegisterImp {
	String DRIVER = "com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306";
	String USER = "root";
	String PASSWORD = "Tangziqi1996";
	String sql = "INSERT into ndroid.login (username, password) VALUE (?, ?)";
	int i;
			
	public int registerUser(UserBeam user) {
		String u = user.getUsername();
		String p = user.getPassword();
		
		// connect to database and store value in table login.
		
		try {
			Class.forName(DRIVER);
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u);
			ps.setString(2, p);
			i = ps.executeUpdate();
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
}
