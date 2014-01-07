/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import java.util.ArrayList;

import com.example.helper.CommonTable;
import com.example.mappe.Document;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author lede92
 */
class TeilvorgaengeTable extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6308502518305308264L;
	private ArrayList<Document> teilvorgaenge;

	public TeilvorgaengeTable(ArrayList<Document> teilvorgaenge) {
		this.setTeilvorgaenge(teilvorgaenge);
		initTable();
	}

	private void initTable() {
		CommonTable table = new CommonTable();
		table.addContainerProperty("Vorgang", String.class, "");
		table.addContainerProperty("Status", String.class, "");
		table.addContainerProperty("Zuwendungs-\nsumme [EUR]", String.class, "");
		table.addContainerProperty("Zahlungs-\nbetrag [EUR]", String.class, "");
		table.addContainerProperty("Zahlungs-\ndatum", String.class, "");

		table.setSelectable(true);
		initLines(table);

		super.addComponent(table);
	}

	private void initLines(CommonTable table) {
		int i = 0;
		for (Document teilvorgang : teilvorgaenge) {
			TeilvorgaeneTableData data = new TeilvorgaeneTableData(teilvorgang);
			table.addItem(
					new Object[] { data.getVorgang(), data.getStatus(),
							data.getZuwendungssumme(),
							data.getZahlungsbetrag(), data.getZahlungsdatum() },
					i++);
		}

	}

	public void setTeilvorgaenge(ArrayList<Document> teilvorgaenge) {
		this.teilvorgaenge = teilvorgaenge;
	}

	public ArrayList<Document> getTeilvorgaenge() {
		return teilvorgaenge;
	}
}
