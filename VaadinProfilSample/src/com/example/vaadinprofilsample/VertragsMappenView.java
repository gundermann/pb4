/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import helper.CommonGuiProblems;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.vaadin.addon.borderlayout.BorderLayout;

import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;

import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * 
 * @author lede92
 */
public class VertragsMappenView extends VerticalLayout implements MappenView {

	private static final long serialVersionUID = 942285785207819440L;
	VertragsMappe currentMappe;
	HashMap<String, Component> guiElemente = new HashMap<String, Component>();
	ArrayList<String> verweise = new ArrayList<String>();

	private HorizontalLayout initSplitPane() {
		HorizontalLayout splitPaneBereich = new HorizontalLayout();

		HorizontalSplitPanel horizontalerSplit = new HorizontalSplitPanel();
		horizontalerSplit.setSplitPosition(30, Sizeable.Unit.PERCENTAGE);

		VerticalSplitPanel vertikalerSplit = new VerticalSplitPanel();
		VerticalLayout obereBereich = initDocumentBaum();
		VerticalLayout untererBereich = initVerweiseBaum();
		vertikalerSplit.setFirstComponent(obereBereich);
		vertikalerSplit.setSecondComponent(untererBereich);

		VerticalLayout right = new VerticalLayout();
		VerticalLayout rightArea = initBearbeitungInhalt();
		right.addComponent(rightArea);

		VerticalLayout leftArea = new VerticalLayout();
		leftArea.setHeight("500px");
		leftArea.addComponent(vertikalerSplit);

		horizontalerSplit.setFirstComponent(leftArea);
		horizontalerSplit.setSecondComponent(right);

		splitPaneBereich.addComponent(horizontalerSplit);
		return splitPaneBereich;
	}

	private MenuBar initMenu() {
		MenuBar menu = new MenuBar();
		MenuBar.MenuItem bearbeitung = menu.addItem("Bearbeitung", null);
		MenuBar.MenuItem drucken = bearbeitung.addItem("Drucken", null);
		MenuBar.MenuItem seitenansicht = bearbeitung.addItem("Seitenansicht",
				null);
		MenuBar.MenuItem schliessen = bearbeitung.addItem("Schlie�en", null);
		MenuBar.MenuItem hilfe = menu.addItem("Hilfe", null, null);
		MenuBar.MenuItem sample = hilfe.addItem("JavaFX sample", null);
		menu.setWidth("100%"); // CSS

		return menu;
	}

	private HorizontalLayout initStatusbar() {
		HorizontalLayout statusbar = new HorizontalLayout();
		Label status = new Label(currentMappe.getStatus());
		Label mappe = new Label(currentMappe.getFp());
		Label original = new Label();
		original.setCaption("Original");
		Button btRefresh = new Button(null);
		FileResource imgAbbrechen = new FileResource(new File(VaadinService
				.getCurrent().getBaseDirectory().getPath()
				+ "/WEB-INF/img/TbCopy.gif"));
		btRefresh.setIcon(imgAbbrechen);
		btRefresh.addStyleName("statusButton");
		guiElemente.put("StatusBarOriginalLabel", original);
		statusbar.addComponents(btRefresh, mappe, status, original);

		return statusbar;
	}

	public VertragsMappenView(String parameter) {
		initMappen(parameter);
		initGUI();
		initController();
	}

	public String getTitle() {
		return currentMappe.getBnrzd().toString() + " " + currentMappe.getAzA();
	}

	private VerticalLayout initDocumentBaum() {
		VerticalLayout documentBox = new VerticalLayout();
		Tree documentTree = new Tree();
		Object[][] documentRoot = new Object[][] { new Object[] { currentMappe
				.getFp() } };
		documentTree.addItem(documentRoot[0][0]);
		appendDocuments(currentMappe, documentTree);
		documentBox.addComponent(documentTree);
		guiElemente.put("Dokumente", documentTree);

		return documentBox;
	}

	private VerticalLayout initVerweiseBaum() {
		VerticalLayout verweiseBox = new VerticalLayout();
		Tree verweiseTree = new Tree();
		Object[][] verweiseRoot = new Object[][] { new Object[] { "Verweise" } };
		verweiseTree.addItem(verweiseRoot[0][0]);

		for (String verweis : verweise) {
			verweiseTree.addItem(verweis);
			verweiseTree.setParent(verweis, verweiseRoot[0][0]);
			verweiseTree.setChildrenAllowed(verweis, false);
		}
		guiElemente.put("Verweise", verweiseTree);
		verweiseBox.addComponent(verweiseTree);
		verweiseBox.setHeight(250, Sizeable.Unit.PIXELS);
		return verweiseBox;
	}

	private VerticalLayout initBearbeitungInhalt() {
		VerticalLayout inhalt = new VerticalLayout();
		guiElemente.put("Bearbeitungsfeld", inhalt);
		return inhalt;
	}

	private VerticalLayout getBearbeitungsFeld() {
		return (VerticalLayout) guiElemente.get("Bearbeitungsfeld");
	}

	private void initGUI() {
		BorderLayout borderLayout = new BorderLayout();
		HorizontalLayout splitPane = initSplitPane();

		GridLayout menuPane = new GridLayout(1, 2);
		MenuBar menu = initMenu();
		HorizontalLayout toolbar = initToolbar();
		guiElemente.put("Toolbar", toolbar);
		menuPane.addComponent(menu, 0, 0);
		menuPane.addComponent(toolbar, 0, 1);
		menuPane.setWidth("100%"); // CSS

		HorizontalLayout statusbar = initStatusbar();
		borderLayout.addComponent(menuPane, BorderLayout.Constraint.NORTH);
		borderLayout.addComponent(splitPane, BorderLayout.Constraint.CENTER);
		borderLayout.addComponent(statusbar, BorderLayout.Constraint.SOUTH);
		this.addComponent(borderLayout);

		showMappe();
	}

	private void initMappen(String parameter) {
		ArrayList<Mappe.VertragsMappe> vertragsMappen = CommonGuiProblems
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
			for (Mappe.VertragsMappe mappe : vertragsMappen) {
				if (mappe.getAzB().equals(parameter)) {
					this.currentMappe = mappe;
				}
			}
		}
	}

	private void appendDocuments(Document doc, Tree tree) {
		for (Document children : doc.getChildren()) {
			tree.addItem(children.getTitel());
			tree.setParent(children.getTitel(), getRootItem(tree));
			tree.setChildrenAllowed(children.getTitel(), false);
		}
	}

	// TODO casten
	private Object getRootItem(Tree tree) {
		Object rootItem = null;
		for (Object i : tree.rootItemIds()) {
			rootItem = i;
			break;
		}
		return rootItem;
	}

	private void initController() {
		initToolbarController();
		initVerweiseBaumController();
		initDocumentTreeController();
	}

	private void initToolbarController() {
		// TODO
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	private void initVerweiseBaumController() {
		getVerweiseTree().setImmediate(true);
		getVerweiseTree().addItemClickListener(new VerweiseClickListenernew());
	}

	private void initDocumentTreeController() {
		getDocumentTree().setImmediate(true);
		getDocumentTree().addItemClickListener(new DokumentClickListener(this));
	}

	private Tree getVerweiseTree() {
		return ((Tree) guiElemente.get("Verweise"));
	}

	private Tree getDocumentTree() {
		return ((Tree) guiElemente.get("Dokumente"));
	}

	public void showDocument(Document doc) {
		if (doc instanceof Vertragsblatt) {
			showVertragsblatt(doc);
		} else if (doc instanceof Vertrag) {
			showVertrag(doc);
		} else if (doc instanceof Auszahlung) {
			showAuszahlung(doc);
		}
	}

	private void showVertragsblatt(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		VertragsblattView view = new VertragsblattView(doc, currentMappe);
		getBearbeitungsFeld().addComponent(view);
	}

	private void showAuszahlung(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new AuszahlungsView(doc));
	}

	private void showVertrag(Document doc) {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new VertragsView(doc));
	}

	private HorizontalLayout initToolbar() {
		HorizontalLayout toolBar = new HorizontalLayout();
		// toolbar.prefWidthProperty().bind(scene.widthProperty());
		String basepath = VaadinService.getCurrent().getBaseDirectory()
				.getPath();

		System.out.println(basepath);

		FileResource imageDrop = new FileResource(new File(basepath
				+ "/WEB-INF/img/TbCopy.gif"));
		FileResource imagePrint = new FileResource(new File(basepath
				+ "/WEB-INF/img/TbPrint.gif"));
		FileResource imageOrg = new FileResource(new File(basepath
				+ "/WEB-INF/img/TbOriginal.gif"));
		FileResource imageGetOrg = new FileResource(new File(basepath
				+ "/WEB-INF/img/TbKopie.gif"));
		FileResource imageHelp = new FileResource(new File(basepath
				+ "/WEB-INF/img/TbHelp.gif"));

		Button btDrop = new Button();
		btDrop.setIcon(imageDrop);
		btDrop.addStyleName("barbutton");
		Button btPrint = new Button();
		btPrint.setIcon(imagePrint);
		btPrint.addStyleName("barbutton");
		btPrint.setEnabled(false);
		Button btLossOrg = new Button();
		btLossOrg.setIcon(imageOrg);
		btLossOrg.addStyleName("barbutton");
		Button btGetOrg = new Button();
		btGetOrg.setIcon(imageGetOrg);
		btGetOrg.addStyleName("barbutton");
		btGetOrg.setEnabled(false);
		Button btHelp = new Button();
		btHelp.setIcon(imageHelp);
		btHelp.addStyleName("barbutton");

		toolBar.addComponents(btDrop, btPrint, btLossOrg, btGetOrg, btHelp);

		return toolBar;
	}

	@Override
	public void showMappe() {
		getBearbeitungsFeld().removeAllComponents();
		getBearbeitungsFeld().addComponent(new AllgDatenView(currentMappe));
	}

	@Override
	public String getCurrentMappenTitle() {
		return currentMappe.getTitel();
	}

	@Override
	public void changeDocView(String documentTitle) {
		for (Document doc : currentMappe.getChildren()) {
			if (documentTitle.contains(doc.getTitel())) {
				showDocument(doc);
				break;
			}
		}
	}
}
