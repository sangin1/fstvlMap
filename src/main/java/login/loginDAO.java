package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import login.loginVO;
import trrsrt.trrsrtVO; 

public class loginDAO {
	final static String dbconnect = "jdbc:mysql://localhost:3306/fstvldb?useUnicode=true&serverTimezone=UTC";
	public loginDAO() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public String addMember(loginVO m) {
		String check="0";
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from login where id='%s'",
					m.getId()));
		){
			if(rs.next()){ 
				check="1";
				return check;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
		){
			String id = m.getId();
			String pwd = m.getPwd();
			stmt.executeUpdate(String.format("insert into login(id, pw) value ('%s', '%s')",
					id,pwd));
		}catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}
	public loginVO login(String id, String pw) {
		String idnum = "0";
		String id1 = "0";
		String pw1 = "0"; 
		loginVO login;
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from login where id='%s' and pw='%s'",
					id,pw));
		){
			if(rs.next()){ 
				idnum = rs.getString("idnum");
				id1 = rs.getString("id"); 
				pw1 = rs.getString("pw");  
				login = new loginVO( idnum,id1,pw1); 
				return login;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		login = new loginVO("0");
		return login;
	}
	public String addFavor(String idnum, String fnum, String tnum) {
		String check="0";
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from favorites where idnum=%s and fnum=%s and tnum=%s",
					idnum,fnum,tnum));
		){
			if(rs.next()){ 
				check="1";
				return check;
			}

		}catch(Exception e){
			e.printStackTrace();
		}
			try(Connection conn = DriverManager.getConnection(
					dbconnect,"root","1234");
				Statement stmt = conn.createStatement();
			){
				stmt.executeUpdate(String.format("insert into favorites(idnum, fnum, tnum) value (%s, %s, %s)",
						idnum,fnum,tnum));
			}catch(Exception e){
				e.printStackTrace();
			}
			return check;
		
	}
	
}
