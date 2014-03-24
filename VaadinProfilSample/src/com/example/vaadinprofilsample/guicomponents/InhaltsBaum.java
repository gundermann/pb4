package com.example.vaadinprofilsample.guicomponents;

import com.example.mappe.Document;
import com.example.vaadinprofilsample.MappenView;
import com.example.vaadinprofilsample.controls.DokumentClickListener;
import com.vaadin.ui.Tree;

public class InhaltsBaum extends Tree {

	private MappenView mappenView;

	public InhaltsBaum(MappenView mappenView) {
		this.mappenView = mappenView;
		Object[][] documentRoot = new Object[][] { new Object[] { mappenView
				.getCurrentMappe().getFp() } };
		addItem(documentRoot[0][0]);
		appendDocuments(mappenView.getCurrentMappe());
		setupController();
	}

	private void setupController() {
		setImmediate(true);
		addItemClickListener(new DokumentClickListener(mappenView));		
	}

	private void appendDocuments(Document doc) {
		for (Document children : doc.getUnterDokumente()) {
			addItem(children.getTitel());
			setParent(children.getTitel(), getRootItem());
			setChildrenAllowed(children.getTitel(), false);
		}
	}

	private Object getRootItem() {
		Object rootItem = null;
		for (Object i : rootItemIds()) {
			rootItem = i;
			break;
		}
		return rootItem;
	}

}
