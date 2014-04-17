package com.example.vaadinprofilsample;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.example.mappe.Document;
import com.example.mappe.Vertrag;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

class VertragsView extends BorderLayout {

	private static final long serialVersionUID = 7176485993775411853L;
	private Map<String, Button> controlbuttons = new HashMap<String, Button>();
	private Vertrag currentTV;
	private String[] stategraphsImagesPaths;
	private VerticalLayout stateArea;

	public VertragsView(Document tv) {
		currentTV = (Vertrag) tv;
		stateArea = new VerticalLayout();
		initStates();
		this.addComponent(stateArea, BorderLayout.Constraint.CENTER);
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
		String status = currentTV.getStatus();
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
		Button btSecond = new Button();

		controlbuttons.put("first", btFirst);
		controlbuttons.put("sec", btSecond);

		control.addComponents(btFirst, btSecond);
		return control;
	}

	private void initStates() {
		stategraphsImagesPaths = new String[4];
		String imageBasepath = VaadinService.getCurrent().getBaseDirectory()
				.getPath()
				+ "/WEB-INF/graph/";

		stategraphsImagesPaths[0] = imageBasepath
				+ "initialisiert.png";
		stategraphsImagesPaths[1] = imageBasepath
				+ "bearbeitung begonnen.png";
		stategraphsImagesPaths[2] = imageBasepath
				+ "mittelfestgelegt.png";
		stategraphsImagesPaths[3] = imageBasepath
				+ "bearbeitungbeendet.png";
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

	private void changeStateID(int state) {
		FileResource graphImage = new FileResource(new File(stategraphsImagesPaths[state]));
		stateArea.removeAllComponents();
		stateArea.addComponent(new Image("", graphImage));
	}

	private void setInitialisiert() {
		changeStateID(INITIALISIERT);

		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setBegonnen() {
		changeStateID(BEARBEITUNG_BEGONNEN);

		controlbuttons.get("first").setCaption("Mittel festlegen");
		controlbuttons.get("sec").setVisible(false);
	}

	private void setFestgelegt() {
		changeStateID(MITTEL_FESTGELEGT);
		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(true);
		controlbuttons.get("sec").setCaption("Bearbeitung beenden");
	}

	private void setBeendet() {
		changeStateID(BEARBEITUNG_BEENDET);
		controlbuttons.get("first").setCaption("Bearbeitung beginnen");
		controlbuttons.get("sec").setVisible(false);
	}

	private static final int INITIALISIERT = 0;
	private static final int BEARBEITUNG_BEGONNEN = 1;
	private static final int MITTEL_FESTGELEGT = 2;
	private static final int BEARBEITUNG_BEENDET = 3;

}
