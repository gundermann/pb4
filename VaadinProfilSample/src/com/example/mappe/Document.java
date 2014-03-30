package com.example.mappe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.vaadinprofilsample.table.TableData;

public class Document implements Serializable, TableData {

	private static final long serialVersionUID = -4229888959960647294L;
	private String titel;
	private ArrayList<Document> unterDokumente;

	@Override
	public HashMap<String, Object[]> getTableValues() {
		HashMap<String, Object[]> values = new HashMap<String, Object[]>();
		Object[] title = {String.valueOf(getTitel()), String.class};
		Object[] status = {String.valueOf(getStatus()), String.class};
		values.put("title", title);
		values.put("status", status);
		return values;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String status;
	
	public ArrayList<Document> getUnterDokumente() {
		if(unterDokumente == null){
			unterDokumente = new ArrayList<Document>();
		}
		return unterDokumente;
	}

	public void setUnterDokumente(ArrayList<Document> unterDokumente) {
		this.unterDokumente = unterDokumente;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}


}
