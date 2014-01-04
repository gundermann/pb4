package Mappe;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertrag extends Document implements Serializable{
	
	public int jahr;
	public String status;
	public float zuwendung;
	private ArrayList<Document> documents = new ArrayList<Document>();
	
	
	public ArrayList<Document> getChildren() {
		return documents;
	}
	
	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	public int getJahr() {
		return jahr;
	}
	public void setJahr(int jahr) {
		this.jahr = jahr;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
