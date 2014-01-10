/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import com.example.helper.CommonGuiProblems;
import com.example.mappe.VertragsMappe;
import com.example.vaadinprofilsample.guicomponents.Label;
import com.example.vaadinprofilsample.guicomponents.gridbag.Constraint;
import com.example.vaadinprofilsample.guicomponents.gridbag.GridBagLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author lede92
 */
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

		GridLayout border = new GridLayout(1, 2);
		Label teilvorgaengeHeader = new Label("Teilvorgänge");
		HorizontalLayout tableTeilvorgaenge = new TeilvorgaengeTable(
				currentMappe.getTeilvorgaenge());
		border.addComponent(teilvorgaengeHeader, 0, 0);
		border.addComponent(tableTeilvorgaenge, 0, 1);

		grid.setContraints(uebersicht, getSimpleConstraint(0, 0));
		grid.setContraints(border, getSimpleConstraint(0, 1));
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

		gbl.setContraints(lbazA, getSimpleConstraint(0, 0));
		gbl.setContraints(lbazB, getSimpleConstraint(0, 1));
		gbl.setContraints(lbamt, getSimpleConstraint(0, 2));
		gbl.setContraints(lbfp, getSimpleConstraint(0, 3));
		gbl.setContraints(lbeuc, getSimpleConstraint(0, 4));
		gbl.setContraints(lberstauszahlung, getSimpleConstraint(0, 5));
		gbl.setContraints(lbstatus, getSimpleConstraint(0, 6));

		gbl.setContraints(azA, getSimpleConstraint(1, 0));
		gbl.setContraints(azB, getSimpleConstraint(1, 1));
		gbl.setContraints(amt, getSimpleConstraint(1, 2));
		gbl.setContraints(fp, getSimpleConstraint(1, 3));
		gbl.setContraints(euc, getSimpleConstraint(1, 4));
		gbl.setContraints(erstauszahlung, getSimpleConstraint(1, 5));
		gbl.setContraints(status, getSimpleConstraint(1, 6));

		return gbl;
	}

	private Constraint getSimpleConstraint(int x, int y) {
		Constraint c = new Constraint();
		c.setGridx(x);
		c.setGridy(y);
		return c;
	}

}
