package trrsrt;

public class trrsrtSearchVO {
	private String mainAddress;
	private String subAddress;
	private String fstvlName;
	
	public trrsrtSearchVO(String mainAddress, String subAddress, String fstvlName) {
		super();
		this.mainAddress = mainAddress;
		this.subAddress = subAddress;
		this.fstvlName = fstvlName;
	}

	public String getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	public String getSubAddress() {
		return subAddress;
	}

	public void setSubAddress(String subAddress) {
		this.subAddress = subAddress;
	}

	public String getFstvlName() {
		return fstvlName;
	}

	public void setFstvlName(String fstvlName) {
		this.fstvlName = fstvlName;
	}
	
	
}
