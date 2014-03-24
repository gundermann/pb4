package com.example.vaadinprofilsample.table;

import java.util.ArrayList;

import com.example.mappe.Document;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author lede92
 */
public class TeilvorgaengeTableView extends HorizontalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6308502518305308264L;
	private ArrayList<Document> teilvorgaenge;

	public TeilvorgaengeTableView(ArrayList<Document> teilvorgaenge) {
		this.setTeilvorgaenge(teilvorgaenge);
		initTable();
		setStyleName("tableview");
	}

	private void initTable() {
		TeilvorgaeneTableModel table = new TeilvorgaeneTableModel(teilvorgaenge);
		super.addComponent(table);
	}


	public void setTeilvorgaenge(ArrayList<Document> teilvorgaenge) {
		this.teilvorgaenge = teilvorgaenge;
	}

	public ArrayList<Document> getTeilvorgaenge() {
		return teilvorgaenge;
	}
}
