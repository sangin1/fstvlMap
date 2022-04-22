package login;

public class loginVO {
	private String idnum;
	private String id;
	private String pwd;
	
	
	public loginVO(String idnum, String id, String pwd) {
		super();
		this.idnum = idnum;
		this.id = id;
		this.pwd = pwd;
	}
	
	public loginVO(String id, String pwd) {
		super();
		this.id = id;
		this.pwd = pwd;
	}
	
	public loginVO(String idnum) {
		super();
		this.idnum = idnum;
	}

	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	} 
	
	
}