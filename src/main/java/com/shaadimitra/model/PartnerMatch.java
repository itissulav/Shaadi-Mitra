package com.shaadimitra.model;

public class PartnerMatch {
	int partnerone_id;
	int partnertwo_id;
	int matchPercent;
	boolean success;
	String matchStatus;
	public PartnerMatch(int partnerone_id, int partnertwo_id, int matchPercent, boolean success, String matchStatus) {
		super();
		this.partnerone_id = partnerone_id;
		this.partnertwo_id = partnertwo_id;
		this.matchPercent = matchPercent;
		this.success = success;
		this.matchStatus = matchStatus;
	}
	public PartnerMatch() {
		super();
	}
	public int getPartnerone_id() {
		return partnerone_id;
	}
	public void setPartnerone_id(int partnerone_id) {
		this.partnerone_id = partnerone_id;
	}
	public int getPartnertwo_id() {
		return partnertwo_id;
	}
	public void setPartnertwo_id(int partnertwo_id) {
		this.partnertwo_id = partnertwo_id;
	}
	public int getMatchPercent() {
		return matchPercent;
	}
	public void setMatchPercent(int matchPercent) {
		this.matchPercent = matchPercent;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMatchStatus() {
		return matchStatus;
	}
	public void setMatchStatus(String matchStatus) {
		this.matchStatus = matchStatus;
	}
	
	
}
