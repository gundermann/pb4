package com.example.vaadinprofilsample.controls;

import com.example.helper.ImageButton;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class OriginalAusleihenButtonCouple implements ClickListener {

	private ImageButton btLoss;
	private ImageButton btGet;

	public OriginalAusleihenButtonCouple(ImageButton btLoss,
			ImageButton btGet) {
				this.btLoss = btLoss;
				this.btGet = btGet;
				
				btLoss.addClickListener(this);
				btGet.addClickListener(this);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		btLoss.setEnabled(!btLoss.isEnabled());
		btGet.setEnabled(!btGet.isEnabled());
		
	}

}
