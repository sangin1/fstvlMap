package trrsrt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
	public List<trrsrtVO> fstvltrrsrt(String olat,String olot,String km) throws IOException{
		
		List<trrsrtVO> trrsrt = new ArrayList<trrsrtVO>();
		String query="";   
		query = (String.format("select * from trrsrt where latitude > 0 and tnum > 0;"));
		try(Connection conn = DriverManager.getConnection(
				dbconnect,"root","1234");
			Statement stmt = conn.createStatement(); 
			ResultSet rs = stmt.executeQuery(query);
				
		){
			while(rs.next()){ 
				trrsrtVO trrsrtvo = new trrsrtVO();
				trrsrtVO trrsrtdvo = new trrsrtVO();
				trrsrtvo.setTrrsrtNm(rs.getString("trrsrtNm"));	
				trrsrtvo.setLatitude(rs.getString("latitude"));
				trrsrtvo.setLongitude(rs.getString("longitude"));
				trrsrtvo.setTnum(rs.getString("tnum"));
				
				double dis = distance(Double.parseDouble(olat),Double.parseDouble(olot),Double.parseDouble(trrsrtvo.getLatitude()),Double.parseDouble(trrsrtvo.getLongitude()));
				
					if(dis <= Double.parseDouble(km)) {
						int km2 = (int) dis;
						trrsrtvo.setKm(Integer.toString(km2));
						trrsrt.add(trrsrtvo);	
					}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return trrsrt;
	}
	public static double distance(double lat1, double lon1, double lat2, double lon2) { 
		double theta = lon1 - lon2; 
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta)); 
		dist = Math.acos(dist); dist = rad2deg(dist); 
		dist = dist * 60 * 1.1515; 
			dist = dist * 1.609344; 
		return (dist); 
		} 
	private static double deg2rad(double deg) { 
		return (deg * Math.PI / 180.0); 
	} 
	private static double rad2deg(double rad) { 
		return (rad * 180 / Math.PI); 
	}

	public trrsrtVO distance2(String olat,String olot,String dlat,String dlot) throws IOException {
		trrsrtVO trrsrtvo = new trrsrtVO();
        StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/distancematrix/json?"); /*URL*/
        urlBuilder.append(URLEncoder.encode("units","UTF-8") + "=" + URLEncoder.encode("metric", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("mode","UTF-8") + "=" + URLEncoder.encode("transit", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("origins","UTF-8") + "=" + URLEncoder.encode(String.format("%s,%s",olat,olot), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("destinations","UTF-8") + "=" + URLEncoder.encode(String.format("%s,%s",dlat,dlot), "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("region","UTF-8") + "=" + URLEncoder.encode("kr", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("key","UTF-8") + "=AIzaSyBv_OQaaCohFpbHGvrUZRQrK_XTOSCWh4I&callback=initMap"); /*Service Key*/
        
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        //System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        try {
			JSONObject objData = (JSONObject)new JSONParser().parse(sb.toString());
			JSONArray rowsData = (JSONArray)objData.get("rows");
			JSONObject elementData = (JSONObject)rowsData.get(0);
			JSONArray elementArrData = (JSONArray)elementData.get("elements");
			JSONObject Data = (JSONObject)elementArrData.get(0);			
			JSONObject distanceData = (JSONObject)Data.get("distance");
			if(distanceData!=null) {
				String kmData = (String)distanceData.get("text");
				String[] kmarr = kmData.split(" ");
				trrsrtvo.setKm(kmarr[0]);
				
				JSONObject durationData = (JSONObject)Data.get("duration");
				String timeData = (String)durationData.get("text");
				String[] time2 = timeData.split(" ");
				trrsrtvo.setHour(time2[0]);
				trrsrtvo.setMin(time2[2]);
			}else {
				trrsrtvo.setKm("-1");
			}
			
						
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return trrsrtvo;
	}

}
