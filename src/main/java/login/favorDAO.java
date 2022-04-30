package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fstvl.fstvlSearchVO;
import fstvl.fstvlVO;

public class favorDAO {
	final static String dbconnect = "jdbc:mariadb://192.168.111.132:3306/newdb?useUnicode=true&serverTimezone=UTC";
	public favorDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	
	public List<fstvlVO> fstvlSearch(String id) {
		List<fstvlVO> fstvl = new ArrayList<fstvlVO>();
		String query="";
			query = (String.format("select distinct * from fdata join favorites where favorites.idnum = %s and fdata.fnum = favorites.fnum;\r\n"
					+ ";"
					,id));
				
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
				
		){
			while(rs.next()){ 
				fstvlVO fstvlvo = new fstvlVO();
				fstvlvo.setFstvlNm(rs.getString("fstvlNm"));	
				fstvlvo.setOpar(rs.getString("opar"));
				fstvlvo.setFstvlStartDate(rs.getString("fstvlStartDate"));
				fstvlvo.setFstvlEndDate(rs.getString("fstvlEndDate"));
				fstvlvo.setFstvlCo(rs.getString("fstvlCo"));
				fstvlvo.setMnnst(rs.getString("mnnst"));
				fstvlvo.setAuspclnstt(rs.getString("auspcInstt"));
				fstvlvo.setSuprtInstt(rs.getString("suprtInstt"));
				fstvlvo.setPhoneNumber(rs.getString("phoneNumber"));
				fstvlvo.setHomepageUrl(rs.getString("homepageUrl"));
				fstvlvo.setRelateinfo(rs.getString("relateinfo"));
				fstvlvo.setLnmadr(rs.getString("lnmadr"));
				fstvlvo.setLatitude(rs.getString("latitude"));
				fstvlvo.setLongitude(rs.getString("longitude"));
				fstvlvo.setReferenceDate(rs.getString("referenceDate"));
				fstvlvo.setInstt_code(rs.getString("instt_code"));
				fstvlvo.setInstt_nm(rs.getString("instt_nm"));
				fstvlvo.setFnum(rs.getString("fnum"));
				fstvl.add(fstvlvo);			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return fstvl;
	}
	public void fnumdel(String idnum, String fnum) {
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
				Statement stmt = conn.createStatement();
				
		){ 		
			stmt.execute(String.format("delete from favorites where idnum = %s and fnum = %s",
					idnum,fnum));
			/*PreparedStatement pstmt = conn.prepareStatement(String.format("detele from employee where c_id = %s and name = '%s'",
					c_id,name));
			pstmt.executeUpdate();  */
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
