package weather;

public class sweatherVO {
	private String[] max = new String[3];
	private String[] min = new String[3];
	private String[] pop = new String[9];
	private String[] pty = new String[9];
	private String[] pcp = new String[9];
	private String[] sno = new String[9]; 
	private String[] sky = new String[9]; 
	private String[] day = new String[3]; 
	public sweatherVO() {
		
	}
	
	public String[] getDay() {
		return day;
	}

	public void setDay(String[] day) {
		this.day = day;
	}

	public String[] getMax() {
		return max;
	}

	public void setMax(String[] max) {
		this.max = max;
	}

	public String[] getMin() {
		return min;
	}

	public void setMin(String[] min) {
		this.min = min;
	}

	public String[] getPop() {
		return pop;
	}

	public void setPop(String[] pop) {
		this.pop = pop;
	}

	public String[] getPty() {
		return pty;
	}

	public void setPty(String[] pty) {
		this.pty = pty;
	}

	public String[] getPcp() {
		return pcp;
	}

	public void setPcp(String[] pcp) {
		this.pcp = pcp;
	}

	public String[] getSno() {
		return sno;
	}

	public void setSno(String[] sno) {
		this.sno = sno;
	}

	public String[] getSky() {
		return sky;
	}

	public void setSky(String[] sky) {
		this.sky = sky;
	}
	
	
	
	
	
	
	
}
