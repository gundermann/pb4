/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.addon.borderlayout.BorderLayout;

import Mappe.Document;
import Mappe.Vertrag;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * 
 * @author lede92
 */
class VertragsView extends BorderLayout {

	private static final long serialVersionUID = 7176485993775411853L;
	private String status;
	private Map<String, Button> controlbuttons = new HashMap<String, Button>();
	private Vertrag currentTV;
	private GridLayout grid;

	public VertragsView(Document tv) {
		currentTV = (Vertrag) tv;

		grid = initStates();
		this.addComponent(grid, BorderLayout.Constraint.CENTER);

		HorizontalLayout controlPane = initButtons();
		initController();

		this.addComponent(controlPane, BorderLayout.Constraint.SOUTH);

		setupOrUpdateStateGraph();
	}

	private void initController() {
		controlbuttons.get("first").addClickListener(
				new Button.ClickListener() {

					@Override
					public void buttonClick(Button.ClickEvent event) {
						currentTV.changeState(controlbuttons.get("first")
								.getCaption());
						setupOrUpdateStateGraph();
					}
				});

		controlbuttons.get("sec").addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				currentTV.changeState(controlbuttons.get("sec").getCaption());
				setupOrUpdateStateGraph();
			}
		});
	}

	protected int state() {
		this.status = currentTV.getStatus();
		if (status.equals("Initialisiert")) {
			return 0;
		} else if (status.equals("Bearbeitung beendet")) {
			return 3;
		} else if (status.equals("Bearbeitung begonnen")) {
			return 1;
		} else {
			return 2;
		}
	}

	private HorizontalLayout initButtons() {
		HorizontalLayout control = new HorizontalLayout();
		Button btFirst = new Button();
		// btFirst.getStyleClass().add("controlpanelbutton");
		Button btSecond = new Button();
		// btSecond.getStyleClass().add("controlpanelbutton");

		controlbuttons.put("first", btFirst);
		controlbuttons.put("sec", btSecond);

		control.addComponents(btFirst, btSecond);
		return control;
	}

	private GridLayout initStates() {
		GridLayout states = new GridLayout(1, 7);

		Label initialisiert = new Label("Initialisiert");
		initialisiert.setId("state");
		initialisiert.setWidth(350, Unit.PIXELS);
		Label begonnen = new Label("Bearbeitung begonnen");
		begonnen.setId("state");
		begonnen.setWidth(350, Unit.PIXELS);
		Label festgelegt = new Label("Mittel festgelegt");
		festgelegt.setId("settedState");
		festgelegt.setWidth(350, Unit.PIXELS);
		Label beendet = new Label("Bearbeitung beendet");
		beendet.setId("state");
		beendet.setWidth(350, Unit.PIXELS);

		states.addComponent(initialisiert, 0, 0);
		// states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 1, 1, 1);
		states.addComponent(begonnen, 0, 2);
		// states.add(CommonGuiProblems.ArrowJumpUp(50, 1), 3, 2, 1, 5);
		// states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 3, 1, 1);
		// states.add(CommonGuiProblems.ArrowUp(50, 350), 1, 3, 1, 1);
		states.addComponent(festgelegt, 0, 4);
		// states.add(CommonGuiProblems.ArrowDown(50, 350), 0, 5, 1, 1);
		states.addComponent(beendet, 0, 6);

		return states;
	}

	private void setupOrUpdateStateGraph() {
		switch (state()) {
		case 0:
			this.setInitialisiert();
			break;
		case 1:
			this.setBegonnen();
			break;
		case 2:
			this.setFestgelegt();
			break;
		case 3:
			this.setBeendet();
			break;
		default:
			break;
		}

	}

	private void changeStateID(String state) {
		for (int i = 0; i < grid.getColumns(); i++) {
			for (int j = 0; j < grid.getRows(); j++) {
				Label label = (Label) grid.getComponent(i, j);
				if (label instanceof Label) {
					if (label.getCaption() == null
							|| label.getCaption().equals(state)) { // getText()
																	// auf NULL
																	// prüfen
						label.setId("settedState");
					} else {
						label.setId("state");
					}
				}
			}
		}
	}

	private void setInitialisiert() {
		changeStateID("Initialisiert");

		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setBegonnen() {
		changeStateID("Bearbeitung begonnen");

		controlbuttons.get("first").setCaption("Mittel festlegen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setFestgelegt() {
		changeStateID("Mittel festgelegt");
		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(true);
		controlbuttons.get("sec").setCaption("Bearbeitung beenden");
	}

	private void setBeendet() {
		changeStateID("Bearbeitung beendet");
		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

}
