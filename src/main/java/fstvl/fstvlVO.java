package fstvl;

public class fstvlVO {
	private String fstvlNm;
	private String opar;
	private String fstvlStartDate;
	private String fstvlEndDate;
	private String fstvlCo;
	private String mnnst;
	private String auspclnstt;
	private String suprtInstt;
	private String PhoneNumber;
	private String homepageUrl;
	private String relateinfo;
	private String lnmadr;
	private String rdnmadr;
	private String latitude;
	private String longitude;
	private String referenceDate;
	private String instt_code;
	private String instt_nm;
	private String fnum;
	
	public fstvlVO(String fstvlNm, String opar, String fstvlStartDate, String fstvlEndDate, String fstvlCo, String mnnst,
			String auspclnstt, String phoneNumber, String homepageUrl, String relateinfo, String rdnmadr, String lnmadr, String latitude,
			String longitude, String referenceDate, String instt_code, String instt_nm, String fnum,String suprtInstt) { 
		this.fstvlNm = fstvlNm;
		this.opar = opar;
		this.fstvlStartDate = fstvlStartDate;
		this.fstvlEndDate = fstvlEndDate;
		this.fstvlCo = fstvlCo;
		this.mnnst = mnnst;
		this.auspclnstt = auspclnstt;
		this.PhoneNumber = phoneNumber;
		this.homepageUrl = homepageUrl;
		this.relateinfo = relateinfo;
		this.rdnmadr = rdnmadr;
		this.lnmadr = lnmadr;
		this.latitude = latitude;
		this.longitude = longitude;
		this.referenceDate = referenceDate;
		this.instt_code = instt_code;
		this.instt_nm = instt_nm;
		this.fnum = fnum;
		this.suprtInstt = suprtInstt;
	}
	public fstvlVO() {
		
	}
	public String getRdnmadr() {
		return rdnmadr;
	}
	public void setRdnmadr(String rdnmadr) {
		this.rdnmadr = rdnmadr;
	}
	public String getSuprtInstt() {
		return suprtInstt;
	}
	public void setSuprtInstt(String suprtInstt) {
		this.suprtInstt = suprtInstt;
	} 
	public String getFstvlNm() {
		return fstvlNm;
	}
	public void setFstvlNm(String fstvlNm) {
		this.fstvlNm = fstvlNm;
	}
	public String getOpar() {
		return opar;
	}
	public void setOpar(String opar) {
		this.opar = opar;
	}
	public String getFstvlStartDate() {
		return fstvlStartDate;
	}
	public void setFstvlStartDate(String fstvlStartDate) {
		this.fstvlStartDate = fstvlStartDate;
	}
	public String getFstvlEndDate() {
		return fstvlEndDate;
	}
	public void setFstvlEndDate(String fstvlEndDate) {
		this.fstvlEndDate = fstvlEndDate;
	}
	public String getFstvlCo() {
		return fstvlCo;
	}
	public void setFstvlCo(String fstvlCo) {
		this.fstvlCo = fstvlCo;
	}
	public String getMnnst() {
		return mnnst;
	}
	public void setMnnst(String mnnst) {
		this.mnnst = mnnst;
	}
	public String getAuspclnstt() {
		return auspclnstt;
	}
	public void setAuspclnstt(String auspclnstt) {
		this.auspclnstt = auspclnstt;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public String getHomepageUrl() {
		return homepageUrl;
	}
	public void setHomepageUrl(String hompageUrl) {
		this.homepageUrl = hompageUrl;
	}
	public String getRelateinfo() {
		return relateinfo;
	}
	public void setRelateinfo(String relateinfo) {
		this.relateinfo = relateinfo;
	}
	public String getLnmadr() {
		return lnmadr;
	}
	public void setLnmadr(String lnmadr) {
		this.lnmadr = lnmadr;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getReferenceDate() {
		return referenceDate;
	}
	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}
	public String getInstt_code() {
		return instt_code;
	}
	public void setInstt_code(String instt_code) {
		this.instt_code = instt_code;
	}
	public String getInstt_nm() {
		return instt_nm;
	}
	public void setInstt_nm(String instt_nm) {
		this.instt_nm = instt_nm;
	}
	public String getFnum() {
		return fnum;
	}
	public void setFnum(String fnum) {
		this.fnum = fnum;
	}

}
