package trrsrt;

public class reviewVO {
	private String renum;
	private String retext;
	private String idnum;
	private String fanum;
	private String tnum;
	private String name;
	
	public reviewVO(String renum, String retext, String idnum, String fanum, String tnum) {
		super();
		this.renum = renum;
		this.retext = retext;
		this.idnum = idnum;
		this.fanum = fanum;
		this.tnum = tnum;
	}
	public reviewVO() {
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getRenum() {
		return renum;
	}
	public void setRenum(String renum) {
		this.renum = renum;
	}
	public String getRetext() {
		return retext;
	}
	public void setRetext(String retext) {
		this.retext = retext;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getFanum() {
		return fanum;
	}
	public void setFanum(String fanum) {
		this.fanum = fanum;
	}
	public String getTnum() {
		return tnum;
	}
	public void setTnum(String tnum) {
		this.tnum = tnum;
	}
	
	
	
}
