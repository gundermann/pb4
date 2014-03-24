package com.example.vaadinprofilsample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.example.helper.CommonGuiProblems;
import com.example.helper.ImageButton;
import com.example.mappe.Document;
import com.example.mappe.VertragsMappe;
import com.example.vaadinprofilsample.guicomponents.CommonMenuBar;
import com.example.vaadinprofilsample.guicomponents.CommonToolBar;
import com.example.vaadinprofilsample.guicomponents.InhaltsBaum;
import com.example.vaadinprofilsample.guicomponents.Label;
import com.example.vaadinprofilsample.guicomponents.VerweiseBaum;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public abstract class MappenView extends VerticalLayout {

	HashMap<String, Component> guiElemente = new HashMap<String, Component>();
	VertragsMappe currentMappe;
	ArrayList<String> verweise = new ArrayList<String>();

	public MappenView(String parameter) {
		initMappen(parameter);
		initGUI();
	}

	protected HorizontalLayout initSplitPane() {
		HorizontalLayout splitPaneBereich = new HorizontalLayout();

		HorizontalSplitPanel horizontalerSplit = new HorizontalSplitPanel();
		horizontalerSplit.setSplitPosition(30, Sizeable.Unit.PERCENTAGE);

		VerticalSplitPanel vertikalerSplit = new VerticalSplitPanel();
		vertikalerSplit.setFirstComponent(initInhaltsBaum());
		vertikalerSplit.setSecondComponent(initVerweiseBaum());

		VerticalLayout right = new VerticalLayout();
		right.addComponent(getBearbeitungsFeld());

		VerticalLayout leftArea = new VerticalLayout();
		leftArea.setHeight("500px");
		leftArea.addComponent(vertikalerSplit);

		horizontalerSplit.setFirstComponent(leftArea);
		horizontalerSplit.setSecondComponent(right);

		splitPaneBereich.addComponent(horizontalerSplit);
		return splitPaneBereich;
	}

	private VerticalLayout initInhaltsBaum() {
		VerticalLayout verweiseBox = new VerticalLayout();
		verweiseBox.addComponent(new InhaltsBaum(this));
		verweiseBox.setHeight(250, Sizeable.Unit.PIXELS);
		return verweiseBox;
	}

	public String getTitle() {
		return currentMappe.getBnrzd().toString() + " " + currentMappe.getAzA();
	}

	private VerticalLayout initVerweiseBaum() {
		VerticalLayout verweiseBox = new VerticalLayout();
		verweiseBox.addComponent(new VerweiseBaum(verweise));
		verweiseBox.setHeight(250, Sizeable.Unit.PIXELS);
		return verweiseBox;
	}

	protected void initGUI() {
		BorderLayout borderLayout = new BorderLayout();
		HorizontalLayout splitPane = initSplitPane();

		GridLayout menuPane = new GridLayout(1, 2);
		menuPane.addComponent(new CommonMenuBar(this), 0, 0);
		menuPane.addComponent(new CommonToolBar(this), 0, 1);
		menuPane.setWidth("100%"); // CSS

		HorizontalLayout statusbar = initStatusbar();
		borderLayout.addComponent(menuPane, BorderLayout.Constraint.NORTH);
		borderLayout.addComponent(splitPane, BorderLayout.Constraint.CENTER);
		borderLayout.addComponent(statusbar, BorderLayout.Constraint.SOUTH);
		this.addComponent(borderLayout);

		showMappe();
	}

	protected HorizontalLayout initStatusbar() {
		HorizontalLayout statusbar = new HorizontalLayout();
		Label status = new Label(currentMappe.getStatus());
		Label mappe = new Label(currentMappe.getFp());
		Label original = new Label();
		original.setCaption("Original");
		FileResource imgAbbrechen = new FileResource(new File(VaadinService
				.getCurrent().getBaseDirectory().getPath()
				+ "/WEB-INF/img/TbCopy.gif"));
		ImageButton btRefresh = new ImageButton(imgAbbrechen);
		btRefresh.addStyleName("statusButton");
		guiElemente.put("StatusBarOriginalLabel", original);
		statusbar.addComponents(btRefresh, mappe, status, original);

		return statusbar;
	}

	public String getCurrentMappenTitle() {
		return currentMappe.getTitel();
	}

	protected void initMappen(String parameter) {
		ArrayList<VertragsMappe> vertragsMappen = CommonGuiProblems
				.ladeMappen();
		if (parameter == null) {
			try {
				verweise.addAll(CommonGuiProblems.findeVerweise(vertragsMappen
						.get(0).getAzB()));
				currentMappe = vertragsMappen.get(0);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} else {
			this.verweise.addAll(CommonGuiProblems.findeVerweise(parameter));
			for (VertragsMappe mappe : vertragsMappen) {
				if (mappe.getAzB().equals(parameter)) {
					this.currentMappe = mappe;
				}
			}
		}
	}

	public void changeDocView(String documentTitle) {
		for (Document doc : currentMappe.getUnterDokumente()) {
			if (documentTitle.contains(doc.getTitel())) {
				showDocument(doc);
				break;
			}
		}
	}

	private VerticalLayout initBearbeitungInhalt() {
		VerticalLayout inhalt = new VerticalLayout();
		guiElemente.put("Bearbeitungsfeld", inhalt);
		return inhalt;
	}

	protected VerticalLayout getBearbeitungsFeld() {
		if (guiElemente.get("Bearbeitungsfeld") == null) {
			initBearbeitungInhalt();
		}
		return (VerticalLayout) guiElemente.get("Bearbeitungsfeld");
	}

	public void showMappe() {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new AllgDatenView(currentMappe));
	}

	protected abstract void showDocument(Document doc);

	public VertragsMappe getCurrentMappe() {
		return currentMappe;
	}
}
