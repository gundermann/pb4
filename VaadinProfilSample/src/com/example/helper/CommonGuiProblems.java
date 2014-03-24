package com.example.helper;

import java.io.IOException;
import java.util.ArrayList;

import com.example.mappe.VertragsMappe;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

public class CommonGuiProblems {

	public static void disableFields(GridLayout parent) {
		for (int i = 0; i < parent.getColumns(); i++) {
			for (int j = 0; j > parent.getRows(); j++) {
				((TextField) parent.getComponent(i, j)).setEnabled(false);
			}
		}
	}

	public static ArrayList<String> findeVerweise(String current) {
		ArrayList<String> verweise = new ArrayList<String>();
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();

		vertragsMappen = ladeMappen();

		for (VertragsMappe mappe : vertragsMappen) {
			if (!current.contains(mappe.getAzB())) {
				verweise.add(mappe.getAzB());
			}
		}

		return verweise;
	}

	public static ArrayList<VertragsMappe> ladeMappen() {
		ArrayList<VertragsMappe> vertragsMappen = new ArrayList<VertragsMappe>();

		MappenLoader xmlReader = new MappenLoader();
		int i = 0;
		while (true) {
			try {
				vertragsMappen.add(xmlReader.mappeLaden(new VertragsMappe(),
						++i));

			} catch (IOException ioe) {
				break;
			}
		}

		return vertragsMappen;
	}

}
