package com.example.mappe;

import java.io.Serializable;

public class Vertrag extends Document implements Serializable{
	
	private static final long serialVersionUID = 7439340379508011150L;
	public int jahr;
	public float zuwendung;

	public int getJahr() {
		return jahr;
	}
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	
	public float getZuwendung() {
		return zuwendung;
	}
	public void setZuwendung(float zuwendung) {
		this.zuwendung = zuwendung;
	}

	public void changeState(String text) {
		if(text.equals("Bearbeitung beginnen")){
			status = "Bearbeitung begonnen";
		}
		else if(text.equals("Mittel festlegen")){
			status = "Mittel festgelegt";
		}
		else{
			status = "Bearbeitung beendet";
		}
	}
	
	

}
