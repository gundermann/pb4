package com.example.vaadinprofilsample.guicomponents;

import java.util.ArrayList;

import com.example.vaadinprofilsample.controls.VerweiseClickListener;
import com.vaadin.ui.Tree;

public class VerweiseBaum extends Tree {

	public VerweiseBaum(ArrayList<String> verweise) {
		Object[][] verweiseRoot = new Object[][] { new Object[] { "Verweise" } };
		addItem(verweiseRoot[0][0]);

		for (String verweis : verweise) {
			addItem(verweis);
			setParent(verweis, verweiseRoot[0][0]);
			setChildrenAllowed(verweis, false);
		}
		setupController();
		setStyleName("view_default_padding");
	}

	private void setupController() {
		setImmediate(true);
		addItemClickListener(new VerweiseClickListener());		
	}

}
