/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import org.vaadin.addon.borderlayout.BorderLayout;

import com.example.helper.CommonGuiProblems;
import com.example.mappe.Document;
import com.example.mappe.VertragsMappe;
import com.example.mappe.Vertragsblatt;
import com.example.vaadinprofilsample.guicomponents.Label;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalSplitPanel;

/**
 * 
 * @author lede92
 */
class VertragsblattView extends HorizontalLayout {

	private static final long serialVersionUID = 1014813638405269942L;
	private Vertragsblatt vertragsblatt;
	private VertragsMappe vertragsMappe;

	public VertragsblattView(Document blatt, Document mappe) {
		vertragsblatt = (Vertragsblatt) blatt;
		vertragsMappe = (VertragsMappe) mappe;
		initTabView();
	}

	private void initTabView() {
		TabSheet tabPane = new TabSheet();

		GridLayout tab1 = initVertragView();
		tab1.setCaption("Vertrag");
		tabPane.addTab(tab1);

		VerticalSplitPanel tab2 = initZuwendungenView();
		tab2.setCaption("Zuwendungen");
		tabPane.addComponent(tab2);

		this.addComponent(tabPane);
	}

	private VerticalSplitPanel initZuwendungenView() {
		VerticalSplitPanel split = new VerticalSplitPanel();

		BorderLayout zuwendungen = new BorderLayout();
		Label headline = new Label("Übersicht je Jahr");
		ZuwendungenDetailTable detailTable = new ZuwendungenDetailTable();
		ZuwendungenUebersichtTable zuwendungsTable = new ZuwendungenUebersichtTable(
				vertragsblatt, detailTable);

		zuwendungen.addComponent(headline, BorderLayout.Constraint.NORTH);
		zuwendungen.addComponent(zuwendungsTable,
				BorderLayout.Constraint.CENTER);

		BorderLayout details = new BorderLayout();
		Label headlineDetails = new Label("Details");

		details.addComponent(headlineDetails, BorderLayout.Constraint.NORTH);
		details.addComponent(detailTable, BorderLayout.Constraint.CENTER);

		split.setFirstComponent(zuwendungen);
		split.setSecondComponent(details);

		return split;
	}

	private GridLayout initVertragView() {
		GridLayout vertrag = new GridLayout();

		BorderLayout vertragspartner = new BorderLayout();
		Label partnerHead = new Label("Vertragspartner");

		GridLayout partnerGrid = new GridLayout();

		Label bnrzd = new Label("BNRZD:");
		TextField tfbnrzd = new TextField();
		Label asNummer = new Label("AS-Nummer:");
		TextField tfAsNummer = new TextField();
		Label bezeichnung = new Label("Name/Bezeichung:");
		TextField tfbezeicnung = new TextField();

		tfbnrzd.setValue(vertragsMappe.getBnrzd());
		tfAsNummer.setValue(vertragsMappe.getAzA());
		tfbezeicnung.setValue(vertragsMappe.getTitel());

		partnerGrid.addComponent(bnrzd, 0, 0);
		partnerGrid.addComponent(tfbnrzd, 1, 0, 4, 1);
		partnerGrid.addComponent(asNummer, 5, 0);
		partnerGrid.addComponent(tfAsNummer, 6, 0, 9, 1);
		partnerGrid.addComponent(bezeichnung, 0, 1);
		partnerGrid.addComponent(tfbezeicnung, 1, 1, 14, 1);

		vertragspartner
				.addComponent(partnerHead, BorderLayout.Constraint.NORTH);
		vertragspartner.addComponent(partnerGrid,
				BorderLayout.Constraint.CENTER);

		BorderLayout vertragsinformationen = new BorderLayout();
		Label infoHead = new Label("Vertragsinformationen");
		GridLayout infoGrid = new GridLayout();

		Label vertragsnummer = new Label("Vertragsnummer:");
		TextField tfvertragsnummer = new TextField();
		Label vertragsmuster = new Label("Vertragsmuster:");
		TextField tfvertragsmuster = new TextField();
		Label vertragsbeginn = new Label("Vertragsbeginn:");
		TextField tfvertragsbeginn = new TextField();
		Label vertragslaufzeit = new Label("Vertragslaufzeit:");
		TextField tfvertragslaufzeit = new TextField();
		Label vertragsabschluss = new Label("Vertragsabschluss:");
		TextField tfvertragsabschluss = new TextField();

		tfvertragsnummer.setValue(vertragsMappe.getAzA());
		tfvertragsbeginn.setValue(vertragsblatt.getVertragsbeginn());
		tfvertragsmuster.setValue("Weide - Wirtschaft");
		tfvertragslaufzeit
				.setValue(String.valueOf(vertragsblatt.getLaufzeit()));
		tfvertragsabschluss.setValue(vertragsblatt.getVertragsabschluss());

		infoGrid.addComponent(vertragsnummer, 0, 0);
		infoGrid.addComponent(tfvertragsnummer, 1, 0, 5, 1);
		infoGrid.addComponent(vertragsmuster, 0, 1);
		infoGrid.addComponent(tfvertragsmuster, 1, 1, 5, 1);
		infoGrid.addComponent(vertragsbeginn, 7, 0);
		infoGrid.addComponent(tfvertragsbeginn, 8, 0, 5, 1);
		infoGrid.addComponent(vertragslaufzeit, 7, 1);
		infoGrid.addComponent(tfvertragslaufzeit, 8, 1, 5, 1);
		infoGrid.addComponent(vertragsabschluss, 7, 2);
		infoGrid.addComponent(tfvertragsabschluss, 8, 2, 5, 1);

		vertragsinformationen.addComponent(infoHead,
				BorderLayout.Constraint.NORTH);
		vertragsinformationen.addComponent(infoGrid,
				BorderLayout.Constraint.CENTER);

		CommonGuiProblems.disableFields(infoGrid);
		CommonGuiProblems.disableFields(partnerGrid);

		vertrag.addComponent(vertragspartner, 0, 0);
		vertrag.addComponent(vertragsinformationen, 0, 1);

		return vertrag;
	}

}
