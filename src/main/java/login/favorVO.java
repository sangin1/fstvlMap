package login;

public class favorVO {
	private String fanum;
	private String idnum;
	private String fnum;
	private String tnum;
	
	public favorVO(String fanum, String idnum, String fnum, String tnum) {
		super();
		this.fanum = fanum;
		this.idnum = idnum;
		this.fnum = fnum;
		this.tnum = tnum;
	}
	
	public String getFanum() {
		return fanum;
	}
	public void setFanum(String fanum) {
		this.fanum = fanum;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getFnum() {
		return fnum;
	}
	public void setFnum(String fnum) {
		this.fnum = fnum;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	
	
}
