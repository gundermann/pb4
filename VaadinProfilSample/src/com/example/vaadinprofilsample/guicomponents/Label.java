package com.example.vaadinprofilsample.guicomponents;

import com.example.helper.StringNotFoundException;
import com.example.helper.Strings;

public class Label extends com.vaadin.ui.Label {

	private static final long serialVersionUID = -5619937792835298597L;

	public Label() {
		super();
		addStyleName("label");
	}

	public Label(String stringKey) {
		try {
			String string = Strings.getString(stringKey);
			if (string != null) {
				setValue(string);
			}
		} catch (StringNotFoundException e) {
			System.out.println(e.getMessage());
			this.setCaption(stringKey);
		} finally {
			addStyleName("label");
		}
	}

}
