package com.example.vaadinprofilsample.guicomponents.gridbag;

import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.GridLayout;

public class GridBagLayout extends GridLayout {

	private static final long serialVersionUID = -4039504349461919623L;
	private int cols = 0;
	private int rows = 0;

	public GridBagLayout() {
		super();
	}

	public void setContraints(AbstractComponent component, Constraint constraint) {
		while (rows < constraint.getGridy() + constraint.getGridHeigth()) {
			rows++;
		}
		super.setRows(rows);
		while (cols < constraint.getGridx() + constraint.getGridWidth()) {
			cols++;
		}
		super.setColumns(cols);

		int x1 = constraint.getGridx();
		int y1 = constraint.getGridy();
		int x2 = x1 + constraint.getGridWidth() - 1;
		int y2 = y1 + constraint.getGridHeigth() - 1;
		if (constraint.getFill() == Constraint.HORIZONTAL) {
			x2 = cols;
		}
		if (constraint.getFill() == Constraint.VERTICAL) {
			y2 = rows;
		}

		component.setSizeFull();
		super.addComponent(component, x1, y1, x2, y2);

		// TODO Insets erst möglich, wenn margin an den Componenten gesetzt
		// werden kann

	}
}
