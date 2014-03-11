package com.example.helper;

import java.util.HashMap;
import java.util.List;

import com.vaadin.ui.Table;

/**
 * 
 * @author lede92
 */
public abstract class CommonTable<T extends TableData> extends Table {

	private static final long serialVersionUID = 7091630112351519990L;

	protected final int COLUMN_COUNT;

	private String[] columnTitle;

	private Class<?>[] columnType;

	private String[] columnReference;

	private int counter = 0;

	public CommonTable() {
		COLUMN_COUNT = getColumnCount();
		columnTitle = new String[COLUMN_COUNT];
		columnType = new Class[COLUMN_COUNT];
		columnReference = new String[COLUMN_COUNT];
	}

	protected abstract int getColumnCount();

	protected void addColumn(String title, String reference) {
		if (counter < COLUMN_COUNT) {
			columnTitle[counter] = title;
			columnReference[counter] = reference;
			counter++;
		} else {
			// Fehlermeldung
		}
	}

	public void setListe(List<T> dataList) {
		HashMap<Integer, Object[]> values = new HashMap<Integer, Object[]>();
		for (T data : dataList) {
			Object[] dataObject = new Object[COLUMN_COUNT];
			for (String referenceKey : data.getAllValues().keySet()) {
				for(int counter = 0; counter < COLUMN_COUNT; counter++)
				if (referenceKey.equals(columnReference[counter])) {
					Object[] valueAndType = data.getAllValues().get(referenceKey);
					dataObject[counter] = valueAndType[VALUE];
					columnType[counter] = (Class<?>) valueAndType[TYPE];
					break;
				}
			}
			values.put(dataList.indexOf(data), dataObject);
		}
		initColumns();
		for(Integer index : values.keySet()){
			addItem(values.get(index), index);
		}
	}

	private void initColumns() {
		for (int i = 0; i < COLUMN_COUNT; i++) {
			addContainerProperty(columnTitle[i], columnType[i], "error");
		}
	}
	
	private final int VALUE = 0;
	private final int TYPE = 1;
}
