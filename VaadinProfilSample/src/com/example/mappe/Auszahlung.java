package com.example.mappe;

import java.io.Serializable;
import java.util.ArrayList;

public class Auszahlung extends Document implements Serializable {

	private static final long serialVersionUID = -585513093921680216L;
	public String status;
	public float zuwendungssumme;
	public float zahlungsbetrag;

	public ArrayList<Document> getChildren() {
		return new ArrayList<Document>();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getZuwendungssumme() {
		return zuwendungssumme;
	}

	public void setZuwendungssumme(float zuwendungssumme) {
		this.zuwendungssumme = zuwendungssumme;
	}

	public float getZahlungsbetrag() {
		return zahlungsbetrag;
	}

	public void setZahlungsbetrag(float zahlungsbetrag) {
		this.zahlungsbetrag = zahlungsbetrag;
	}

}
