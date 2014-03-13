package com.example.vaadinprofilsample;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class VerweiseClickListener implements ItemClickListener {

	private static final long serialVersionUID = 7485607606297292219L;

	@Override
	public void itemClick(ItemClickEvent event) {
		String selectedDocument = event.getItemId().toString();
		if (!selectedDocument.equals("Verweise")) {
			ladeUndOeffneEntsprechendeMappe(selectedDocument);
		}
	}

	private void ladeUndOeffneEntsprechendeMappe(String selectedDocument) {
		final VertragsMappenView neueVertragsMappenView = new VertragsMappenView(
				selectedDocument);
		
		VaadinProfilSampleUI.tabsheet.addTab(neueVertragsMappenView,
				neueVertragsMappenView.getTitle());
		VaadinProfilSampleUI.tabsheet.getTab(neueVertragsMappenView)
				.setClosable(true);
	}

}
