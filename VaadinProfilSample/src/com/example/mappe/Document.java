package com.example.mappe;

import java.io.Serializable;
import java.util.ArrayList;

public class Document implements Serializable {

	private static final long serialVersionUID = -4229888959960647294L;
	private String titel;

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public ArrayList<Document> getChildren() {
		return null;
	}

}
