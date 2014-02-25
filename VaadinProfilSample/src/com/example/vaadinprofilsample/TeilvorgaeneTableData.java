/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import java.util.ArrayList;
import java.util.List;

import javax.sql.CommonDataSource;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;

import com.example.helper.CommonTable;
import com.example.helper.TableData;
import com.example.mappe.Auszahlung;
import com.example.mappe.Document;
import com.example.mappe.Vertrag;

/**
 * 
 * @author lede92
 */
class TeilvorgaeneTableData extends CommonTable {

	public TeilvorgaeneTableData(ArrayList<Document> teilvorgaenge) {
		addContainerProperty("Vorgang", String.class, "");
		addContainerProperty("Status", String.class, "");
		addContainerProperty("Zuwendungs-\nsumme [EUR]", String.class, "");
		addContainerProperty("Zahlungs-\nbetrag [EUR]", String.class, "");
		addContainerProperty("Zahlungs-\ndatum", String.class, "");

		setSelectable(true);
		initLines(teilvorgaenge);
	}

	private void initLines(ArrayList<Document> docs) {
		for (Document teilvorgang : docs) {
			teilvorgang.
			tableDataList.add(new TeilvorgaeneTableData(teilvorgang));
		}
		table.setListe(tableDataList);

	}


}
