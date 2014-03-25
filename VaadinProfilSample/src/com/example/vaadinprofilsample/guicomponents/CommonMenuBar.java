package com.example.vaadinprofilsample.guicomponents;

import com.example.vaadinprofilsample.MappenView;
import com.example.vaadinprofilsample.controls.SchliessenCommand;
import com.vaadin.ui.MenuBar;

public class CommonMenuBar extends MenuBar {

	public CommonMenuBar(MappenView mappenView) {
		super();
		MenuBar.MenuItem bearbeitung = addItem("Bearbeitung", null);
		MenuBar.MenuItem drucken = bearbeitung.addItem("Drucken", null);
		MenuBar.MenuItem seitenansicht = bearbeitung.addItem("Seitenansicht",
				null);
		MenuBar.MenuItem schliessen = bearbeitung.addItem("Schlieﬂen", null);
		MenuBar.MenuItem hilfe = addItem("Hilfe", null, null);
		MenuBar.MenuItem sample = hilfe.addItem("JavaFX sample", null);

		schliessen.setCommand(new SchliessenCommand(mappenView));
	}
}
