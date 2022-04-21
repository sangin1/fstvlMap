package fstvl;
 
public class fstvlSearchVO {
	private String startDate;
	private String endDate;
	private String mainAddress;
	private String subAddress;
	private String fstvlName;
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public fstvlSearchVO(String startDate, String endDate, String mainAddress, String subAddress, String fstvlName) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.mainAddress = mainAddress;
		this.subAddress = subAddress;
		this.fstvlName = fstvlName;
	}
	public fstvlSearchVO(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
}
