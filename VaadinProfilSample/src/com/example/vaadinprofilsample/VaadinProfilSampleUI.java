package com.example.vaadinprofilsample;

import javax.servlet.annotation.WebServlet;

import com.example.vaadin.VertragsMappenView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("vaadinprofilsample")
public class VaadinProfilSampleUI extends UI {

	static TabSheet tabsheet = new TabSheet();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinProfilSampleUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		setContent(tabsheet);

		VertragsMappenView vertragsMappe = new VertragsMappenView(null);
		tabsheet.addTab(vertragsMappe).setCaption(vertragsMappe.getTitle());
		tabsheet.getTab(vertragsMappe).setClosable(true);

	}

}