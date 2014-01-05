/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import Mappe.Document;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import helper.CommonTable;
import helper.TableData;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lede92
 */
class TeilvorgaengeTable extends HorizontalLayout {

    private ArrayList<Document> teilvorgaenge;
    
    public TeilvorgaengeTable(ArrayList<Document> teilvorgaenge) {
        this.setTeilvorgaenge(teilvorgaenge);
	initTable();
    }
    
    private void initTable() {
		CommonTable table = new CommonTable();
                table.addContainerProperty("Vorgang", String.class, ""); //(VALUE, TYPE, DEFAULT)
                table.addContainerProperty("Status", String.class, ""); //(VALUE, TYPE, DEFAULT)
                table.addContainerProperty("Zuwendungs-\nsumme [EUR]", String.class, ""); //(VALUE, TYPE, DEFAULT)
                table.addContainerProperty("Zahlungs-\nbetrag [EUR]", String.class, ""); //(VALUE, TYPE, DEFAULT)
                table.addContainerProperty("Zahlungs-\ndatum", String.class, ""); //(VALUE, TYPE, DEFAULT)
		//TableColumn<TeilvorgaeneTableData, String> vorgangCol = new TableColumn<TeilvorgaeneTableData, String>("Vorgang");
		//vorgangCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("vorgang"));
		//TableColumn<TeilvorgaeneTableData, String> statusCol = new TableColumn<TeilvorgaeneTableData, String>("Status");
		//statusCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("status"));
		//TableColumn<TeilvorgaeneTableData, String> zuwendungssummeCol = new TableColumn<TeilvorgaeneTableData, String>("Zuwendungs-\nsumme [EUR]");
		//zuwendungssummeCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zuwendungssumme"));
		//TableColumn<TeilvorgaeneTableData, String> zahlungsbetragCol = new TableColumn<TeilvorgaeneTableData, String>("Zahlungs-\nbetrag [EUR]");
		//zahlungsbetragCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zahlungsbetrag"));
		//TableColumn<TeilvorgaeneTableData, String> zahlungdatumCol = new TableColumn<TeilvorgaeneTableData, String>("Zahlungs-\ndatum");
		//zahlungdatumCol.setCellValueFactory(new PropertyValueFactory<TeilvorgaeneTableData, String>("zahlungsdatum"));
		//stable.getColumns().addAll(vorgangCol, statusCol, zuwendungssummeCol, zahlungsbetragCol, zahlungdatumCol);
		
		
		//table.getColumns().get(4).setMinWidth(70);
		//table.getColumns().get(4).setMinWidth(70);
		//table.getColumns().get(2).setMinWidth(110);
		//table.getColumns().get(3).setMinWidth(100);
		//table.getColumns().get(4).setMinWidth(90);
		
                table.setSelectable(true);
		initLines(table);

		//table.setPrefWidth(512);
//		table.prefWidthProperty().bind(((HBox) getParent()).prefWidthProperty());
		
		//super.getStyleClass().add("table-hauptfenster");
		super.addComponent(table);
	}

	private void initLines(CommonTable table) {
//            ObservableList<TeilvorgaeneTableData> vorgaenge = FXCollections.observableArrayList();
//		for(Document teilvorgang : teilvorgaenge){
//			vorgaenge.add(new TeilvorgaeneTableData(teilvorgang));
//			
//		}
//		table.setItems(vorgaenge);
            
            
            int i = 0;
		for(Document teilvorgang : teilvorgaenge){
                        TeilvorgaeneTableData data = new TeilvorgaeneTableData(teilvorgang);
			table.addItem(new Object[]{ data.getVorgang(), data.getStatus(), data.getZuwendungssumme(), data.getZahlungsbetrag(), data.getZahlungsdatum()}, i++);
		}
		
	}

	public void setTeilvorgaenge(ArrayList<Document> teilvorgaenge) {
		this.teilvorgaenge = teilvorgaenge;
	}

	public ArrayList<Document> getTeilvorgaenge() {
		return teilvorgaenge;
	}
}
