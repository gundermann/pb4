package com.example.vaadinprofilsample;

import com.example.mappe.Document;
import com.example.mappe.Vertrag;
import com.example.mappe.Vertragsblatt;

public class VertragsMappenView extends MappenView {

	public VertragsMappenView(String parameter) {
		super(parameter);
	}

	public void showDocument(Document doc) {
		if (doc instanceof Vertragsblatt) {
			showVertragsblatt(doc);
		} else if (doc instanceof Vertrag) {
			showVertrag(doc);
		}
	}

	private void showVertragsblatt(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		VertragsblattView view = new VertragsblattView(doc, currentMappe);
		getBearbeitungsFeld().addComponent(view);
	}

	private void showVertrag(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new VertragsView(doc));
	}

}
