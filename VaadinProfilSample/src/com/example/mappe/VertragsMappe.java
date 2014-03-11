package com.example.mappe;

import java.io.Serializable;

public class VertragsMappe extends Document implements Serializable {

	private static final long serialVersionUID = -534222818259724148L;
	private String azA = "123";
	private String azB;
	private String bnrzd;
	private String amt;
	private String fp;
	private String euCode;
	private String erstzahlungsjahr;
	private String status;

	public String getBnrzd() {
		return bnrzd;
	}

	public void setBnrzd(String bnrzd) {
		this.bnrzd = bnrzd;
	}

	public String getAzA() {
		return azA;
	}

	public void setAzA(String azA) {
		this.azA = azA;
	}

	public String getAzB() {
		return azB;
	}

	public void setAzB(String azB) {
		this.azB = azB;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
		setTitel(fp);
	}

	public String getEuCode() {
		return euCode;
	}

	public void setEuCode(String euCode) {
		this.euCode = euCode;
	}

	public String getErstzahlungsjahr() {
		return erstzahlungsjahr;
	}

	public void setErstzahlungsjahr(String erstzahlungsjahr) {
		this.erstzahlungsjahr = erstzahlungsjahr;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
