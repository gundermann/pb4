package com.example.vaadinprofilsample;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;

public class VerweiseClickListenernew implements ItemClickListener {

	private static final long serialVersionUID = 7485607606297292219L;

	@Override
	public void itemClick(ItemClickEvent event) {
		// TODO changed - important for pb
		String selectedDocument = event.getItemId().toString();
		if (!selectedDocument.equals("Verweise")) {
			ladeUndOeffneEntsprechendeMappe(selectedDocument);
		}
	}

	private void ladeUndOeffneEntsprechendeMappe(String selectedDocument) {
		VertragsMappenView neueVertragsMappenView = new VertragsMappenView(
				selectedDocument);
		VaadinProfilSampleUI.tabsheet.addTab(neueVertragsMappenView,
				neueVertragsMappenView.getTitle());
		VaadinProfilSampleUI.tabsheet.getTab(neueVertragsMappenView)
				.setClosable(true);
	}

}
