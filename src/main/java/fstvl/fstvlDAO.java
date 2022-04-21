package fstvl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fstvl.fstvlVO; 

public class fstvlDAO {
	final static String dbconnect = "jdbc:mysql://localhost:3306/newdb?useUnicode=true&serverTimezone=UTC";
	public fstvlDAO() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public List<fstvlVO> fstvlSearch(fstvlSearchVO fsv) {
		List<fstvlVO> fstvl = new ArrayList<fstvlVO>();
		String query="";
		if(fsv.getFstvlName()=="" && fsv.getMainAddress()=="") {
			query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0;"
					,fsv.getStartDate(),fsv.getEndDate()));
		}
		else if(fsv.getMainAddress()=="") {
			query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and fstvlNm like '%%%s%%';"
					,fsv.getStartDate(),fsv.getEndDate(),fsv.getFstvlName()));
		}
		else if(fsv.getFstvlName()=="") {
			if(fsv.getMainAddress().equals("전체")) {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0;"
						,fsv.getStartDate(),fsv.getEndDate()));
			}else if(fsv.getSubAddress().equals("전체")) {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and rdnmadr like '%%%s%%';"
						,fsv.getStartDate(),fsv.getEndDate(),fsv.getMainAddress()));
			}else {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and rdnmadr like '%%%s%%' and rdnmadr like '%%%s%%';"
						,fsv.getStartDate(),fsv.getEndDate(),fsv.getMainAddress(),fsv.getSubAddress()));
			}			
		}
		else {
			if(fsv.getMainAddress().equals("전체")) {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and fstvlNm like '%%%s%%';"
						,fsv.getStartDate(),fsv.getEndDate(),fsv.getFstvlName()));
			}else if(fsv.getSubAddress().equals("전체")) {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and rdnmadr like '%%%s%% and fstvlNm like '%%%s%%'';"
						,fsv.getStartDate(),fsv.getEndDate(),fsv.getMainAddress(),fsv.getFstvlName()));
			}else {
				query = (String.format("select * from fdata where fstvlStartDate between '%s' and '%s' and latitude > 0 and rdnmadr like '%%%s%%' and rdnmadr like '%%%s%%' and fstvlNm like '%%%s%%';"
						,fsv.getStartDate(),fsv.getEndDate(),fsv.getMainAddress(),fsv.getSubAddress(),fsv.getFstvlName()));
			}
		}
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
	public fstvlVO mapData(String fnum) { 
		fstvlVO fstvlvo = new fstvlVO();
		String[] array;
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from fdata where fnum = %s;",fnum));
		){
			while(rs.next()){ 				
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
				fstvlvo.setRdnmadr(rs.getString("rdnmadr"));
				fstvlvo.setLnmadr(rs.getString("lnmadr"));
				fstvlvo.setLatitude(rs.getString("latitude"));
				fstvlvo.setLongitude(rs.getString("longitude"));
				fstvlvo.setReferenceDate(rs.getString("referenceDate"));
				fstvlvo.setInstt_code(rs.getString("instt_code"));
				fstvlvo.setInstt_nm(rs.getString("instt_nm"));
				fstvlvo.setFnum(rs.getString("fnum")); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		array = fstvlvo.getFstvlStartDate().split(" ");
		fstvlvo.setFstvlStartDate(array[0]);
		array = fstvlvo.getFstvlEndDate().split(" ");
		fstvlvo.setFstvlEndDate(array[0]);
		return fstvlvo;
	}
}
