package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Bashekim extends User{
	Connection con = conn.connDb();
	Statement st=null;
	ResultSet rs = null;
	PreparedStatement preparedStatement=null;
	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		
		
		
	}
	public Bashekim() {}
	
	public ArrayList<User> getDoctorList() throws SQLException{
		ArrayList<User> list = new ArrayList <>();
		User obj;
		try {
			st=con.createStatement();
			rs= st.executeQuery("SELECT*FROM user WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			
		}
			return list;
	}
	//doktor ekleme metodu
	public boolean addDoctor(String tcno,String password,String name) throws SQLException {
		
		String query = "INSERT INTO user "+"(tcno,password,name,type) VALUES "+ " (?,?,?,?)";
		boolean key = false;
		try {
			st=con.createStatement();
			preparedStatement=con.prepareStatement(query);

			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
		{
			return true;
		}
		else {
			
			return false;
		}
		
		
	}

}













