package com.example.vaadinprofilsample.guicomponents;

import java.io.File;

import com.example.helper.ImageButton;
import com.example.vaadinprofilsample.MappenView;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.HorizontalLayout;

public class CommonToolBar extends HorizontalLayout {

	public CommonToolBar(MappenView mappenView) {
		String imageBasepath = VaadinService.getCurrent().getBaseDirectory()
				.getPath()
				+ "/WEB-INF/img/";

		FileResource imageDrop = new FileResource(new File(imageBasepath
				+ "TbCopy.gif"));
		FileResource imagePrint = new FileResource(new File(imageBasepath
				+ "TbPrint.gif"));
		FileResource imageOrg = new FileResource(new File(imageBasepath
				+ "TbOriginal.gif"));
		FileResource imageGetOrg = new FileResource(new File(imageBasepath
				+ "TbKopie.gif"));
		FileResource imageHelp = new FileResource(new File(imageBasepath
				+ "TbHelp.gif"));

		ImageButton btDrop = new ImageButton(imageDrop);
		ImageButton btPrint = new ImageButton(imagePrint);
		ImageButton btLossOrg = new ImageButton(imageOrg);
		ImageButton btGetOrg = new ImageButton(imageGetOrg);
		ImageButton btHelp = new ImageButton(imageHelp);

		this.addComponents(btDrop, btPrint, btLossOrg, btGetOrg, btHelp);
	}
	
	private void initController() {
		// TODO
	}

}
