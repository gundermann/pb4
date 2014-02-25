package com.example.helper;

import java.util.List;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;

import com.vaadin.ui.Table;

/**
 *
 * @author lede92
 */
public class CommonTable<T extends TableData> extends Table{
    
    
	private static final long serialVersionUID = 7091630112351519990L;

	
	public void setListe(List<T> dataList){
        for(TableData data : dataList){
        	Object[] dataObject = new Object[data.getClass().getDeclaredFields().length];
            for( Observable dataValue : data.getAllValues()){
            	dataValue.
            }
        	addItem(data.getAllValues());
        }
        
    }
}
