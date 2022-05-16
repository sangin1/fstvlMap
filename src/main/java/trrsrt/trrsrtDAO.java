package trrsrt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class trrsrtDAO {
	final static String dbconnect = "jdbc:mysql://localhost:3306/fstvldb?useUnicode=true&serverTimezone=UTC";
	public trrsrtDAO() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public List<trrsrtVO> trrsrtSearch(trrsrtSearchVO fsv) {
		List<trrsrtVO> trrsrt = new ArrayList<trrsrtVO>();
		String query="";
		if(fsv.getFstvlName()=="") {
			if(fsv.getMainAddress().equals("전체")) {
				query = (String.format("select * from trrsrt where latitude > 0 and tnum > 0;"));
			}else if(fsv.getSubAddress().equals("전체")) {
				query = (String.format("select * from trrsrt where latitude > 0 and rdnmadr like '%%%s%%' and tnum > 0;"
						,fsv.getMainAddress()));
			}else {
				query = (String.format("select * from trrsrt where latitude > 0 and rdnmadr like '%%%s%%' and rdnmadr like '%%%s%%' and tnum > 0;"
						,fsv.getMainAddress(),fsv.getSubAddress()));
			}			
		}
		else {
			if(fsv.getMainAddress().equals("전체")) {
				query = (String.format("select * from trrsrt where latitude > 0 and trrsrtNm like '%%%s%%' and tnum > 0;"
						,fsv.getFstvlName()));
			}else if(fsv.getSubAddress().equals("전체")) {
				query = (String.format("select * from trrsrt where latitude > 0 and rdnmadr like '%%%s%% and trrsrtNm like '%%%s%%'' and tnum > 0;"
						,fsv.getMainAddress(),fsv.getFstvlName()));
			}else {
				query = (String.format("select * from trrsrt where latitude > 0 and rdnmadr like '%%%s%%' and rdnmadr like '%%%s%%' and trrsrtNm like '%%%s%%' and tnum > 0;"
						,fsv.getMainAddress(),fsv.getSubAddress(),fsv.getFstvlName()));
			}
		}
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
				
		){
			while(rs.next()){ 
				trrsrtVO trrsrtvo = new trrsrtVO();
				trrsrtvo.setTrrsrtNm(rs.getString("trrsrtNm"));	
				trrsrtvo.setTrrsrtSe(rs.getString("trrsrtSe"));
				trrsrtvo.setRdnmadr(rs.getString("rdnmadr"));
				trrsrtvo.setLnmadr(rs.getString("lnmadr"));
				trrsrtvo.setRdnmadr(rs.getString("rdnmadr"));
				trrsrtvo.setLatitude(rs.getString("latitude"));
				trrsrtvo.setLongitude(rs.getString("longitude"));
				trrsrtvo.setAr(rs.getString("ar"));
				trrsrtvo.setCnvnncFclty(rs.getString("cnvnncFclty"));
				trrsrtvo.setStayngInfo(rs.getString("stayngInfo"));
				trrsrtvo.setAmsmtFclty(rs.getString("amsmtFclty"));
				trrsrtvo.setClturFclty(rs.getString("clturFclty"));
				trrsrtvo.setHospitalityFclty(rs.getString("hospitalityFclty"));
				trrsrtvo.setSportFclty(rs.getString("sportFclty"));
				trrsrtvo.setAppnDate(rs.getString("appnDate"));
				trrsrtvo.setAceptncCo(rs.getString("aceptncCo"));
				trrsrtvo.setPrkplceCo(rs.getString("prkplceCo"));
				trrsrtvo.setTrrsrtIntrcn(rs.getString("trrsrtIntrcn"));
				trrsrtvo.setPhoneNumber(rs.getString("phonrNumber"));
				trrsrtvo.setInstitutionNm(rs.getString("institutionNm"));
				trrsrtvo.setReferenceDate(rs.getString("referenceDate"));
				trrsrtvo.setInstt_nm(rs.getString("instt_nm"));
				trrsrtvo.setTnum(rs.getString("tnum"));
				trrsrt.add(trrsrtvo);			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return trrsrt;
	}
	public trrsrtVO trrData(String tnum) { 
		trrsrtVO trrsrtvo = new trrsrtVO();
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement();
			 
			ResultSet rs = stmt.executeQuery(String.format("select * from trrsrt where tnum = %s;",tnum));
		){
			while(rs.next()){ 				
				trrsrtvo.setTrrsrtNm(rs.getString("trrsrtNm"));	
				trrsrtvo.setTrrsrtSe(rs.getString("trrsrtSe"));
				trrsrtvo.setRdnmadr(rs.getString("rdnmadr"));
				trrsrtvo.setLnmadr(rs.getString("lnmadr"));
				trrsrtvo.setLatitude(rs.getString("latitude"));
				trrsrtvo.setLongitude(rs.getString("longitude"));
				trrsrtvo.setAr(rs.getString("ar"));
				trrsrtvo.setCnvnncFclty(rs.getString("cnvnncFclty"));
				trrsrtvo.setStayngInfo(rs.getString("stayngInfo"));
				trrsrtvo.setAmsmtFclty(rs.getString("amsmtFclty"));
				trrsrtvo.setClturFclty(rs.getString("clturFclty"));
				trrsrtvo.setHospitalityFclty(rs.getString("hospitalityFclty"));
				trrsrtvo.setSportFclty(rs.getString("sportFclty"));
				trrsrtvo.setAppnDate(rs.getString("appnDate"));
				trrsrtvo.setAceptncCo(rs.getString("aceptncCo"));
				trrsrtvo.setPrkplceCo(rs.getString("prkplceCo"));
				trrsrtvo.setTrrsrtIntrcn(rs.getString("trrsrtIntrcn"));
				trrsrtvo.setPhoneNumber(rs.getString("phonrNumber"));
				trrsrtvo.setInstitutionNm(rs.getString("institutionNm"));
				trrsrtvo.setReferenceDate(rs.getString("referenceDate"));
				trrsrtvo.setInstt_nm(rs.getString("instt_nm"));
				trrsrtvo.setTnum(rs.getString("tnum")); 
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return trrsrtvo;
	}
}
