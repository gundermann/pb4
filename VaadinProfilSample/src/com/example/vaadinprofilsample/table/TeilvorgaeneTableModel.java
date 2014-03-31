/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample.table;

import java.util.ArrayList;

import com.example.mappe.Document;

/**
 * 
 * @author lede92
 */
public class TeilvorgaeneTableModel extends CommonTable<Document> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2922225623471520071L;

	public TeilvorgaeneTableModel(ArrayList<Document> teilvorgaenge) {
		super();
		addColumn("Vorgang", "title");
		addColumn("Status", "status");
		addColumn("Zuwendungssumme [EUR]", "zuwendungssumme");
		addColumn("Zahlungsbetrag [EUR]", "zahlungsbetrag");

		setSelectable(true);
		this.setListe(teilvorgaenge);
	}

	@Override
	protected int getColumnCount() {
		return 4;
	}

}
