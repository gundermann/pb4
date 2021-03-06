package com.example.vaadinprofilsample;

import javax.servlet.annotation.WebServlet;

import com.example.vaadinprofilsample.trythings.table.TableTest;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

@Theme("vaadinprofilsample")
public class VaadinProfilSampleUI extends UI {

	private static final long serialVersionUID = 6536258243942344949L;
	public static TabSheet tabsheet = new TabSheet();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = VaadinProfilSampleUI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = -4756021649722347743L;
	}

	
	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		tabsheet.setSizeFull();
		setContent(tabsheet);
		VertragsMappenView vertragsMappe = new VertragsMappenView(null);
		tabsheet.addTab(vertragsMappe).setCaption(vertragsMappe.getTitle());
		tabsheet.getTab(vertragsMappe).setClosable(true);
		
		TableTest tabletest = new TableTest();
		tabsheet.addTab(tabletest);
	}

}