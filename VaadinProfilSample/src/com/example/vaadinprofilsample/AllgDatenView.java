package com.example.vaadinprofilsample;

import com.example.helper.CommonGuiProblems;
import com.example.mappe.VertragsMappe;
import com.example.vaadinprofilsample.guicomponents.Label;
import com.example.vaadinprofilsample.guicomponents.gridbag.GridBagLayout;
import com.example.vaadinprofilsample.table.TeilvorgaengeTableView;
import com.vaadin.ui.HorizontalLayout;

class AllgDatenView extends HorizontalLayout {

	private static final long serialVersionUID = 6550453322676475529L;
	VertragsMappe currentMappe;

	public AllgDatenView(VertragsMappe currentMappe) {
		this.currentMappe = currentMappe;
		GridBagLayout grid = new GridBagLayout();

		HorizontalLayout uebersicht = new HorizontalLayout();
		GridBagLayout innerGridBag = initGridBag();
		uebersicht.addComponent(innerGridBag);
		CommonGuiProblems.disableFields(innerGridBag);

		GridBagLayout border = new GridBagLayout();
		Label teilvorgaengeHeader = new Label("tv");
		HorizontalLayout tableTeilvorgaenge = new TeilvorgaengeTableView(
				currentMappe.getUnterDokumente());
		border.setContraints(teilvorgaengeHeader, CommonGuiProblems.getSimpleConstraint(0, 0));
		border.setContraints(tableTeilvorgaenge, CommonGuiProblems.getSimpleConstraint(0, 1));

		grid.setContraints(uebersicht,  CommonGuiProblems.getSimpleConstraint(0, 0));
		grid.setContraints(border,  CommonGuiProblems.getSimpleConstraint(0, 1));
		this.addComponent(grid);

	}

	private GridBagLayout initGridBag() {
		Label lbazA = new Label("azA");
		Label lbazB = new Label("azB");
		Label lbamt = new Label("amt");
		Label lbfp = new Label("fp");
		Label lbeuc = new Label("eucode");
		Label lberstauszahlung = new Label("ezj");
		Label lbstatus = new Label("status");
		Label azA = new Label();
		Label azB = new Label();
		Label amt = new Label();
		Label fp = new Label();
		Label euc = new Label();
		Label erstauszahlung = new Label();
		Label status = new Label();

		azA.setValue(currentMappe.getAzA());
		azB.setValue(currentMappe.getAzB());
		amt.setValue(currentMappe.getAmt());
		fp.setValue(currentMappe.getFp());
		euc.setValue(currentMappe.getEuCode());
		erstauszahlung.setValue(currentMappe.getErstzahlungsjahr());
		status.setValue(currentMappe.getStatus());

		GridBagLayout gbl = new GridBagLayout();

		gbl.setContraints(lbazA,  CommonGuiProblems.getSimpleConstraint(0, 0));
		gbl.setContraints(lbazB,  CommonGuiProblems.getSimpleConstraint(0, 1));
		gbl.setContraints(lbamt,  CommonGuiProblems.getSimpleConstraint(0, 2));
		gbl.setContraints(lbfp,  CommonGuiProblems.getSimpleConstraint(0, 3));
		gbl.setContraints(lbeuc,  CommonGuiProblems.getSimpleConstraint(0, 4));
		gbl.setContraints(lberstauszahlung,  CommonGuiProblems.getSimpleConstraint(0, 5));
		gbl.setContraints(lbstatus,  CommonGuiProblems.getSimpleConstraint(0, 6));

		gbl.setContraints(azA, CommonGuiProblems.getSimpleConstraint(1, 0));
		gbl.setContraints(azB,  CommonGuiProblems.getSimpleConstraint(1, 1));
		gbl.setContraints(amt,  CommonGuiProblems.getSimpleConstraint(1, 2));
		gbl.setContraints(fp,  CommonGuiProblems.getSimpleConstraint(1, 3));
		gbl.setContraints(euc,  CommonGuiProblems.getSimpleConstraint(1, 4));
		gbl.setContraints(erstauszahlung,  CommonGuiProblems.getSimpleConstraint(1, 5));
		gbl.setContraints(status,  CommonGuiProblems.getSimpleConstraint(1, 6));

		return gbl;
	}

	

}
