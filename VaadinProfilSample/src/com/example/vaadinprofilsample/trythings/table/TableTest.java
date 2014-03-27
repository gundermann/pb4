package com.example.vaadinprofilsample.trythings.table;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;

public class TableTest extends HorizontalLayout{


	public TableTest() {
		setSizeFull();
		initTable();
	}

	private void initTable() {
		Table table = new Table("TestTable");
		table.addContainerProperty("TestSpalte", Integer.class, 2);
		
		table.addItem(new Object[]{"testString"});
		table.addItem(new Object[]{"3"});

		addComponent(table);
	}
}
