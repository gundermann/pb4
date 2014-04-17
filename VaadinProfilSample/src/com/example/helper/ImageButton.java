package com.example.helper;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Button;

/*
 * Funktioniert aufgrund der Buttongr��e nur mit dem runo-Theme.
 * Eine Anfrage f�r die Gr��eneinstellung der Button mittels CSS besteht bereits
 */
public class ImageButton extends Button {

	private static final long serialVersionUID = 7042568710068902431L;

	public ImageButton(FileResource imgAbbrechen) {
		super();
		setIcon(imgAbbrechen);
	}

}
