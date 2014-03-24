package com.example.vaadinprofilsample.controls;

import com.example.vaadinprofilsample.VertragsMappenView;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;

public class SchliessenCommand implements Command {

	private static final long serialVersionUID = 655966665872189783L;
	private VertragsMappenView vertragsMappenView;

	public SchliessenCommand(VertragsMappenView vertragsMappenView) {
		this.vertragsMappenView = vertragsMappenView;
	}

	@Override
	public void menuSelected(MenuItem selectedItem) {
		vertragsMappenView.getUI().close();
	}

}
