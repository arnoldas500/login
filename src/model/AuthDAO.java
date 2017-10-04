package model;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
	
	
	//method to create connection to database
	private static Connection connect() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/portal";
			String usernameSQL = "root";
			String passwordSQL = "";
			connection = DriverManager.getConnection(url,usernameSQL,passwordSQL);
			System.out.println("Connection successful!");
		} catch (ClassNotFoundException e) {
			
			//sendErrorRedirect(request, response, "/signup.jsp", e);
			//request.setAttribute("errorMessage", "Username existed");
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return connection;
	}

	public static int checkUserNameAvailable(ModelMVC m, String sql3) {
		//String sql3 = "select * from User where username=?";
		int k = 0;
		String dbName2 = null;
		Connection connection = connect();
		try(PreparedStatement ps3 = connection.prepareStatement(sql3)){
			ps3.setString(1, m.getUsername());
			//ps3.setString(2, password);
			//ps.setString("jb2", jb2);
			//ps.setString(3, roles);
			//fetch the data and store it somewhere 
			ResultSet resultSet = ps3.executeQuery();
			//PrintWriter out = response.getWriter();
			while(resultSet.next()) {
				dbName2 = resultSet.getString("username");
				//dbPassword = resultSet.getString("password");
				//dbRole = resultSet.getString("role");
			}
			System.out.println("Username from database is : "+ dbName2);
			//out.println("jbUSEr is **************  "+test);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		if( dbName2 == null) {
			k++;
		} else {
			k = 0;
		}
		
		return k;
	}
	
	//method to insert new users into database
	public static int signupUser(ModelMVC m, String sql) {
		//String sql = "insert into User(username,password,role) values(?,?,?)";
		int i = 0;
		Connection connection = connect();
		try(PreparedStatement ps = connection.prepareStatement(sql)){
			//setting the values
			ps.setString(1, m.getUsername());
			ps.setString(2, m.getPassword());
			ps.setString(3, m.getRole());
			//now need to execute this statement
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return i;
	}

	public static int signupUser_Profile(ModelMVC m, String sql2) {
		// String sql2 = "insert into User_Profile(firstName,lastname) values(?,?)";
		int j = 0;
		Connection connection = connect();
		try(PreparedStatement ps2 = connection.prepareStatement(sql2)){
			ps2.setString(1, m.getFirstname());
			ps2.setString(2, m.getLastname());
			//now need to execute this statement
			j = ps2.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return j;
	}

	

	

}
