/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadinprofilsample;

import Mappe.VertragsMappe;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import helper.CommonGuiProblems;

/**
 *
 * @author lede92
 */
class MappenView extends HorizontalLayout {
    
    VertragsMappe currentMappe;

    public MappenView(VertragsMappe currentMappe) {
        this.currentMappe = currentMappe;
	GridLayout grid =  new GridLayout(1, 2);

	HorizontalLayout uebersicht = new HorizontalLayout();
	GridLayout innerGrid = new GridLayout(2, 7);
	Label lbazA = new Label("Aktenzeichen A:");
	Label lbazB = new Label("Aktenzeichen B:");
	Label lbamt = new Label("Amt:");
	Label lbfp = new Label("Förderprogramm:");
	Label lbeuc = new Label("EU-Code");
	Label lberstauszahlung = new Label("Erstauszahlungsjahr:");
	Label lbstatus = new Label("Status:");
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
	
	innerGrid.addComponent(lbazA, 0, 0);
	innerGrid.addComponent(azA, 1, 0);
	innerGrid.addComponent(lbazB, 0, 1);
	innerGrid.addComponent(azB, 1, 1);
	innerGrid.addComponent(lbamt, 0, 2);
	innerGrid.addComponent(amt, 1, 2);
	innerGrid.addComponent(lbfp, 0, 3);
	innerGrid.addComponent(fp, 1, 3);
	innerGrid.addComponent(lbeuc, 0, 4);
	innerGrid.addComponent(euc, 1, 4);
	innerGrid.addComponent(lberstauszahlung, 0, 5);
	innerGrid.addComponent(erstauszahlung, 1, 5);
	innerGrid.addComponent(lbstatus, 0, 6);
	innerGrid.addComponent(status, 1, 6);
	
	
	uebersicht.addComponent(innerGrid);
	
	CommonGuiProblems.disableFields(innerGrid);
	
	GridLayout border = new GridLayout(1, 2);
	Label teilvorgaengeHeader = new Label("Teilvorgänge");
	HorizontalLayout tableTeilvorgaenge = new TeilvorgaengeTable(currentMappe.getTeilvorgaenge());
	border.addComponent(teilvorgaengeHeader, 0, 0);
	border.addComponent(tableTeilvorgaenge, 0, 1);
	
	grid.addComponent(uebersicht, 0, 0);
	grid.addComponent(border, 0, 1);
	this.addComponent(grid);
        
    }
    
}
