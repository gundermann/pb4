/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import com.example.mappe.Auszahlung;
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
		} else if (doc instanceof Auszahlung) {
			showAuszahlung(doc);
		}
	}

	private void showVertragsblatt(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		VertragsblattView view = new VertragsblattView(doc, currentMappe);
		getBearbeitungsFeld().addComponent(view);
	}

	private void showAuszahlung(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new AuszahlungsView(doc));
	}

	private void showVertrag(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new VertragsView(doc));
	}

}
