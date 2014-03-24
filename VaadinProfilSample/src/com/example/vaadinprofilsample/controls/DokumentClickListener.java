package com.example.vaadinprofilsample.controls;

import com.example.vaadinprofilsample.MappenView;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class DokumentClickListener implements ItemClickListener {

	private static final long serialVersionUID = 3798063542338140402L;

	private MappenView mappenView;

	public DokumentClickListener(MappenView vertragsMappenView) {
		mappenView = vertragsMappenView;
	}

	@Override
	public void itemClick(ItemClickEvent event) {
		// TODO changed - important for pb
		String selectedDocument = event.getItemId().toString();
		if (selectedDocument.contains(mappenView.getCurrentMappenTitle())) {
			mappenView.showMappe();
		} else {
			mappenView.changeDocView(selectedDocument);
		}
	}
}
