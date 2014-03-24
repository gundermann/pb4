package com.example.vaadinprofilsample.controls;

import com.example.vaadinprofilsample.MappenView;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class SchliessenCommand implements Command {

	private static final long serialVersionUID = 655966665872189783L;
	private MappenView vertragsMappenView;

	public SchliessenCommand(MappenView mappenView) {
		this.vertragsMappenView = mappenView;
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		vertragsMappenView.getUI().close();
	}

}
