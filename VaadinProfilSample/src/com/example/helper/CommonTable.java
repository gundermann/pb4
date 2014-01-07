/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.helper;

import com.vaadin.ui.Table;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author lede92
 */
public class CommonTable extends Table{
    
    public CommonTable(){
        super();
    }
    
    public void setItems(ObservableList<? extends TableData> dataList){
        for(TableData data : dataList){
            Object[] dataObject = new Object[data.getAllValues().size()];
        
            int i = 0;
            for(SimpleStringProperty value : data.getAllValues()){
                dataObject[i] = value.getValue();
                i++;
            }
            this.addItem(dataObject);
        }
    }
}
