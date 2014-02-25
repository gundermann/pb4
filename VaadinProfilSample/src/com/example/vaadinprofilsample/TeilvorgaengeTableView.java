/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import java.util.ArrayList;
import java.util.List;

import com.example.helper.CommonTable;
import com.example.mappe.Document;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

/**
 * 
 * @author lede92
 */
class TeilvorgaengeTableView extends HorizontalLayout {

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
		TeilvorgaeneTableData table = new TeilvorgaeneTableData(teilvorgaenge);
		super.addComponent(table);
	}


	public void setTeilvorgaenge(ArrayList<Document> teilvorgaenge) {
		this.teilvorgaenge = teilvorgaenge;
	}

	public ArrayList<Document> getTeilvorgaenge() {
		return teilvorgaenge;
	}
}
