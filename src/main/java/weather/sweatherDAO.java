package weather;

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
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fstvl.fstvlDAO;

public class sweatherDAO {
	final static String dbconnect = "jdbc:mysql://localhost:3306/fstvldb?useUnicode=true&serverTimezone=UTC";
	public sweatherDAO() {
		try {
			//Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) { 
			e1.printStackTrace();
		}
	}
	public sweatherVO sweatherS(String l, String m) throws IOException{
		sweatherVO svo = new sweatherVO();
		String start = "",twodate = "",threedate="";  
		String sido = "";
		String[] address;
		String one,two,x="",y="";
		LocalDate now = LocalDate.now();
		String yearNow = Integer.toString(now.getYear());
		String monNow = Integer.toString(now.getMonthValue());
		String dayNow = Integer.toString(now.getDayOfMonth());
		//현제날짜
		if(Integer.parseInt(monNow)<10){
			if(Integer.parseInt(dayNow)<10) {
				start = yearNow+"0"+monNow+"0"+dayNow;
			}
			else {
				start = yearNow+"0"+monNow+""+dayNow;
			}
		}else{
			if(Integer.parseInt(dayNow)<10) {
				start = yearNow+""+monNow+"0"+dayNow;
			}
			else {
				start = yearNow+""+monNow+""+dayNow;
			}
		}
		
		//윤년계산 및 2,3 일후 계산
		if(((Integer.parseInt(yearNow)%4==0)&&(Integer.parseInt(yearNow)%100!=0))||(Integer.parseInt(yearNow)%400==0)){
			if((monNow.equals("1")||monNow.equals("3")||monNow.equals("5")||monNow.equals("7")||monNow.equals("8")||monNow.equals("10"))&&dayNow.equals("31")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"02";
				}else {
					twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
				}
			}else if((monNow.equals("4")||monNow.equals("6")||monNow.equals("9")||monNow.equals("11"))&&dayNow.equals("30")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"02";
				}else {
					twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
				}
			}else if((monNow.equals("1")||monNow.equals("3")||monNow.equals("5")||monNow.equals("7")||monNow.equals("8")||monNow.equals("10"))&&dayNow.equals("30")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
				}else {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				}
			}else if((monNow.equals("4")||monNow.equals("6")||monNow.equals("9")||monNow.equals("11"))&&dayNow.equals("29")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
				}else {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				}
			}else if(monNow.equals("12")&&dayNow.equals("31")) {
				twodate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				threedate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"02";
			}else if(monNow.equals("12")&&dayNow.equals("30")) {
				twodate = Integer.toString(now.getYear()+1)+""+monNow+""+Integer.toString(now.getDayOfMonth()+1);
				threedate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"01";
			}
			else if(monNow.equals("2")&&dayNow.equals("29")) {
				twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
			}else if(monNow.equals("2")&&dayNow.equals("28")) {
				twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
				threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
			}else {
				if(now.getMonthValue()>9) {
					if((now.getDayOfMonth()+1)>9) {
						twodate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+1);
						threedate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+2);
					}else {
						twodate = yearNow+monNow+"0"+Integer.toString(now.getDayOfMonth()+1);
						if((now.getDayOfMonth()+2)>9){
							threedate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+2);
						}else {
							threedate = yearNow+monNow+"0"+Integer.toString(now.getDayOfMonth()+2);
						}
					}
				}else {
					if((now.getDayOfMonth()+1)>9) {
						twodate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+1);
						threedate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+2);
					}else {
						twodate = yearNow+"0"+monNow+"0"+Integer.toString(now.getDayOfMonth()+1);
						if((now.getDayOfMonth()+2)>9){
							threedate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+2);
						}else {
							threedate = yearNow+"0"+monNow+"0"+Integer.toString(now.getDayOfMonth()+2);
						}
					}
				}
			}
		}else {
			if((monNow.equals("1")||monNow.equals("3")||monNow.equals("5")||monNow.equals("7")||monNow.equals("8")||monNow.equals("10"))&&dayNow.equals("31")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"02";
				}else {
					twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
				}
			}else if((monNow.equals("4")||monNow.equals("6")||monNow.equals("9")||monNow.equals("11"))&&dayNow.equals("30")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"02";
				}else {
					twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
				}
			}else if((monNow.equals("1")||monNow.equals("3")||monNow.equals("5")||monNow.equals("7")||monNow.equals("8")||monNow.equals("10"))&&dayNow.equals("30")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
				}else {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				}
			}else if((monNow.equals("4")||monNow.equals("6")||monNow.equals("9")||monNow.equals("11"))&&dayNow.equals("29")) {
				if((now.getMonthValue()+1)>9) {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+""+Integer.toString(now.getMonthValue()+1)+"01";
				}else {
					twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
					threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				}
			}else if(monNow.equals("12")&&dayNow.equals("31")) {
				twodate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				threedate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"02";
			}else if(monNow.equals("12")&&dayNow.equals("30")) {
				twodate = Integer.toString(now.getYear()+1)+""+monNow+""+Integer.toString(now.getDayOfMonth()+1);
				threedate = Integer.toString(now.getYear()+1)+"0"+Integer.toString(now.getMonthValue()+1)+"01";
			}
			else if(monNow.equals("2")&&dayNow.equals("28")) {
				twodate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
				threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"02";
			}else if(monNow.equals("2")&&dayNow.equals("27")) {
				twodate = yearNow+"0"+monNow+""+Integer.toString(now.getDayOfMonth()+1);
				threedate = yearNow+"0"+Integer.toString(now.getMonthValue()+1)+"01";
			}else {
				if(now.getMonthValue()>9) {
					if((now.getDayOfMonth()+1)>9) {
						twodate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+1);
						threedate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+2);
					}else {
						twodate = yearNow+monNow+"0"+Integer.toString(now.getDayOfMonth()+1);
						if((now.getDayOfMonth()+2)>9){
							threedate = yearNow+monNow+Integer.toString(now.getDayOfMonth()+2);
						}else {
							threedate = yearNow+monNow+"0"+Integer.toString(now.getDayOfMonth()+2);
						}
					}
				}else {
					if((now.getDayOfMonth()+1)>9) {
						twodate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+1);
						threedate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+2);
					}else {
						twodate = yearNow+"0"+monNow+"0"+Integer.toString(now.getDayOfMonth()+1);
						if((now.getDayOfMonth()+2)>9){
							threedate = yearNow+"0"+monNow+Integer.toString(now.getDayOfMonth()+2);
						}else {
							threedate = yearNow+"0"+monNow+"0"+Integer.toString(now.getDayOfMonth()+2);
						}
					}
				}
			}
		}
		//x,y좌표 읽기
		if(m.equals("")) {
			sido = l;
		}else {
			sido=m;
		}
		address = sido.split(" ");
		one = address[0];
		if(one.equals("서울특별시")||one.equals("인천광역시")||one.equals("대전광역시")||one.equals("대구광역시")||one.equals("광주광역시")||one.equals("부산광역시")||one.equals("울산광역시")||one.equals("세종특별자치시")) {
			try(Connection conn = DriverManager.getConnection(
					dbconnect,"root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select * from sweather where one='%s'",
						one));
			){
				if(rs.next()){ 
					x = rs.getString("x");
					y = rs.getString("y");
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			two = address[1];
			try(Connection conn = DriverManager.getConnection(
					dbconnect,"root","1234");
				Statement stmt = conn.createStatement();
				 
				ResultSet rs = stmt.executeQuery(String.format("select * from sweather where one='%s' and two like '%s%%'",
						one,two.substring(0, 2)));
			){
				if(rs.next()){ 
					x = rs.getString("x");
					y = rs.getString("y");
				}
	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=uL6jfJrP5BaS5eMJkqCYENN142PV4gDmo3oKsHs53Vs4MHp7X6rQgc6z4eM5%2BZTwqHjXQUumdIJmigSh%2Fu52vw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(start, "UTF-8")); /*‘21년 6월 28일발표*/
        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0200", "UTF-8")); /*05시 발표*/
        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); /*예보지점의 X 좌표값*/
        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); /*예보지점의 Y 좌표값*/
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
        //System.out.println(sb.toString());
        
        try {
			JSONObject objData = (JSONObject)new JSONParser().parse(sb.toString());
			JSONObject resData = (JSONObject)objData.get("response");
			JSONObject bodyData = (JSONObject)resData.get("body");
			JSONObject itemsData = (JSONObject)bodyData.get("items");
			JSONArray itemData = (JSONArray)itemsData.get("item");
			String[] max = new String[3];
			String[] min = new String[3];
			String[] day = new String[3];
			String[] pop = new String[9];
			String[] pty = new String[9];
			String[] pcp = new String[9];
			String[] sno = new String[9]; 
			String[] sky = new String[9]; 
			//System.out.println(itemData.get(0));
			JSONObject tmp;
			for(int i=0; i<itemData.size(); i++){				
				tmp = (JSONObject)itemData.get(i);
				if(tmp.get("category").equals("POP")&&tmp.get("fcstTime").equals("0600")) {
					if(tmp.get("fcstDate").equals(start)) {
						pop[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pop[3] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pop[6] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PTY")&&tmp.get("fcstTime").equals("0600")) {
					if(tmp.get("fcstDate").equals(start)) {
						pty[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pty[3] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pty[6] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PCP")&&tmp.get("fcstTime").equals("0600")) {
					if(tmp.get("fcstDate").equals(start)) {
						pcp[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pcp[3] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pcp[6] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SNO")&&tmp.get("fcstTime").equals("0600")) {
					if(tmp.get("fcstDate").equals(start)) {
						sno[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sno[3] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sno[6] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SKY")&&tmp.get("fcstTime").equals("0600")) {
					if(tmp.get("fcstDate").equals(start)) {
						sky[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sky[3] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sky[6] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("POP")&&tmp.get("fcstTime").equals("1800")) {
					if(tmp.get("fcstDate").equals(start)) {
						pop[2] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pop[5] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pop[8] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PTY")&&tmp.get("fcstTime").equals("1800")) {
					if(tmp.get("fcstDate").equals(start)) {
						pty[2] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pty[5] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pty[8] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PCP")&&tmp.get("fcstTime").equals("1800")) {
					if(tmp.get("fcstDate").equals(start)) {
						pcp[2] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pcp[5] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pcp[8] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SNO")&&tmp.get("fcstTime").equals("1800")) {
					if(tmp.get("fcstDate").equals(start)) {
						sno[2] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sno[5] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sno[8] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SKY")&&tmp.get("fcstTime").equals("1800")) {
					if(tmp.get("fcstDate").equals(start)) {
						sky[2] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sky[5] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sky[8] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("POP")&&tmp.get("fcstTime").equals("1200")) {
					if(tmp.get("fcstDate").equals(start)) {
						pop[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pop[4] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pop[7] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PTY")&&tmp.get("fcstTime").equals("1200")) {
					if(tmp.get("fcstDate").equals(start)) {
						pty[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pty[4] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pty[7] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("PCP")&&tmp.get("fcstTime").equals("1200")) {
					if(tmp.get("fcstDate").equals(start)) {
						pcp[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						pcp[4] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						pcp[7] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SNO")&&tmp.get("fcstTime").equals("1200")) {
					if(tmp.get("fcstDate").equals(start)) {
						sno[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sno[4] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sno[7] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("SKY")&&tmp.get("fcstTime").equals("1200")) {
					if(tmp.get("fcstDate").equals(start)) {
						sky[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						sky[4] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						sky[7] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("TMX")) {
					if(tmp.get("fcstDate").equals(start)) {
						max[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						max[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						max[2] = (String)tmp.get("fcstValue");
					}
				}else if(tmp.get("category").equals("TMN")) {
					if(tmp.get("fcstDate").equals(start)) {
						min[0] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(twodate)) {
						min[1] = (String)tmp.get("fcstValue");
					}else if(tmp.get("fcstDate").equals(threedate)) {
						min[2] = (String)tmp.get("fcstValue");
					}
				}
			}
			day[0] = start.substring(start.length()-4,start.length());
			day[1] = twodate.substring(twodate.length()-4,twodate.length());
			day[2] = threedate.substring(threedate.length()-4,threedate.length());
			svo.setMax(max);
			svo.setMin(min);
			svo.setPop(pop);
			svo.setPcp(pcp);
			svo.setPty(pty);
			svo.setSky(sky);
			svo.setSno(sno);
			svo.setDay(day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return svo;
	}
}
