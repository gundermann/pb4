package com.example.mappe;

import java.util.HashMap;

public class Auszahlung extends Document {

	private static final long serialVersionUID = -585513093921680216L;
	public float zuwendungssumme;
	public float zahlungsbetrag;

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

	@Override
	public HashMap<String, Object[]> getAllValues() {
		HashMap<String, Object[]> values = super.getAllValues();
		Object[] zahlungsbetrag = {String.valueOf(getZahlungsbetrag()), String.class};
		Object[] zuwendungssumme = {String.valueOf(getZuwendungssumme()), String.class};
		values.put("zahlungsbetrag", zahlungsbetrag);
		values.put("zuwendungssumme", zuwendungssumme);
		return values;
	}

}
