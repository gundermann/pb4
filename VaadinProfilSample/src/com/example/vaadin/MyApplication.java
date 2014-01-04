/*
 * MyApplication.java
 *
 * Created on 23. August 2013, 16:55
 */

package com.example.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;

/**
 * 
 * @author lede92
 * @version
 */

@Theme("vaadinprofilsampletheme")
public class MyApplication extends UI {

	static TabSheet tabsheet = new TabSheet();

	@Override
	protected void init(VaadinRequest request) {
		setContent(tabsheet);

		VertragsMappenView vertragsMappe = new VertragsMappenView(null);
		tabsheet.addTab(vertragsMappe).setCaption(vertragsMappe.getTitle());
		tabsheet.getTab(vertragsMappe).setClosable(true);

	}

}
