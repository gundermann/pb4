package com.example.vaadinprofilsample.guicomponents;

import java.io.File;

import com.example.helper.CommonGuiProblems;
import com.example.helper.ImageButton;
import com.example.mappe.VertragsMappe;
import com.example.vaadinprofilsample.guicomponents.gridbag.GridBagLayout;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;

public class StatusBar extends HorizontalLayout {
	
	private final String STATUSBAR_BUTTON = "statusBarButton";

	public StatusBar(VertragsMappe currentMappe) {
		GridBagLayout grid = new GridBagLayout();
		Label status = new Label(currentMappe.getStatus());
		Label mappe = new Label(currentMappe.getTitel());
		Label original = new Label();
		original.setCaption("Original");
		FileResource imgRefresh = new FileResource(new File(VaadinService
				.getCurrent().getBaseDirectory().getPath()
				+ "/WEB-INF/img/SbRefresh.gif"));
		ImageButton refresh = new ImageButton(imgRefresh);
		refresh.setPrimaryStyleName(STATUSBAR_BUTTON);
		grid.setContraints(refresh, CommonGuiProblems.getSimpleConstraint(0, 0));
		grid.setContraints(status, CommonGuiProblems.getSimpleConstraint(1, 0));
		grid.setContraints(mappe, CommonGuiProblems.getSimpleConstraint(2, 0));
		grid.setContraints(original, CommonGuiProblems.getSimpleConstraint(3, 0));
		addComponents(grid);
		addStyleName("statusBar");
		setSpacing(false);
		setMargin(false);
		setSizeFull();
	}

}
