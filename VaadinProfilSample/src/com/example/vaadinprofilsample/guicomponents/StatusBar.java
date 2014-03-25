package com.example.vaadinprofilsample.guicomponents;

import java.io.File;

import com.example.helper.ImageButton;
import com.example.mappe.VertragsMappe;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;

public class StatusBar extends HorizontalLayout {

	public StatusBar(VertragsMappe currentMappe) {
		Label status = new Label(currentMappe.getStatus());
		Label mappe = new Label(currentMappe.getFp());
		Label original = new Label();
		original.setCaption("Original");
		FileResource imgAbbrechen = new FileResource(new File(VaadinService
				.getCurrent().getBaseDirectory().getPath()
				+ "/WEB-INF/img/TbCopy.gif"));
		ImageButton btRefresh = new ImageButton(imgAbbrechen);
		btRefresh.addStyleName("statusButton");
		addComponents(btRefresh, mappe, status, original);
	}

}
