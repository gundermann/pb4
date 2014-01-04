/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vaadin;

import Mappe.Auszahlung;
import Mappe.Document;
import Mappe.Vertrag;
import Mappe.VertragsMappe;
import Mappe.Vertragsblatt;
import com.vaadin.data.Property;
import com.vaadin.server.FileResource;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import helper.CommonGuiProblems;
import helper.SAXLesen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.vaadin.addon.borderlayout.BorderLayout;

/**
 *
 * @author lede92
 */
public class VertragsMappenView extends VerticalLayout{
     
        VertragsMappe currentMappe;
	HashMap<String, Component> guiElemente = new HashMap<String, Component>();
	ArrayList<String> verweise = new ArrayList<String>();
    
        
   
    private HorizontalLayout initSplitPane() {
        HorizontalLayout splitPaneBereich = new HorizontalLayout();

        HorizontalSplitPanel horizontalerSplit = new HorizontalSplitPanel();
        horizontalerSplit.setSplitPosition(30, Sizeable.Unit.PERCENTAGE);		//horizontalerSplit.setDividerPositions(0.3);   

        VerticalSplitPanel vertikalerSplit = new VerticalSplitPanel();		//vertikalerSplit.setOrientation(Orientation.VERTICAL);

	VerticalLayout obereBereich = initDocumentBaum();
	VerticalLayout untererBereich = initVerweiseBaum();

	VerticalLayout right = new VerticalLayout();
	VerticalLayout rightArea = initBearbeitungInhalt();
	right.addComponent(rightArea);//setCenter(rightArea);
	VerticalLayout leftArea = new VerticalLayout();
                
        vertikalerSplit.setFirstComponent(obereBereich);
	vertikalerSplit.setSecondComponent(untererBereich);

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
		
	MenuBar.MenuItem drucken = bearbeitung.addItem("Drucken", null); //(CAPTION, ACTION)
	MenuBar.MenuItem seitenansicht = bearbeitung.addItem("Seitenansicht", null);
	MenuBar.MenuItem schliessen = bearbeitung.addItem("Schließen", null);
		
     	MenuBar.MenuItem hilfe = menu.addItem("Hilfe", null, null);
		
	MenuBar.MenuItem sample = hilfe.addItem("JavaFX sample", null);
        
        menu.setWidth("100%"); //CSS

	return menu;
    }

    private HorizontalLayout initStatusbar() {
        HorizontalLayout statusbar = new HorizontalLayout();  //VBox
        //HorizontalSplitPanel split = new HorizontalSplitPanel();  //Splitpane
        //statusbar.getStyleClass().add("statusbar");

        
		
	Label status = new Label(currentMappe.getStatus());
		//status.setTextAlignment(TextAlignment.RIGHT);
		//status.getStyleClass().add("statusLabel");
		//status.setMinWidth(300);
	Label mappe = new Label(currentMappe.getFp());
		//mappe.setTextAlignment(TextAlignment.RIGHT);
		//mappe.getStyleClass().add("statusLabel");
		//mappe.setMinWidth(300);
	Label original = new Label();
		//original.setTextAlignment(TextAlignment.RIGHT);
	original.setCaption("Original"); //setText
		//original.getStyleClass().add("statusLabel");
		//original.setMinWidth(100);
                
        Button btRefresh = new Button(null);
        FileResource imgAbbrechen = new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getPath()+"/img/TbRefresh20x20.gif"));
        btRefresh.setIcon(imgAbbrechen);
	btRefresh.addStyleName("statusButton");
		
	guiElemente.put("StatusBarOriginalLabel", original);
		
	statusbar.addComponents(btRefresh, mappe, status, original); //getChildren().addAll(...)

		
	return statusbar;
    }

    public VertragsMappenView (String parameter) {
        initMappen(parameter);
        initGUI();
        initController();
    }
     
    public String getTitle(){
        return currentMappe.getBnrzd().toString() + " " + currentMappe.getAzA();
    }

    private VerticalLayout initDocumentBaum() {
        VerticalLayout documentBox = new VerticalLayout();

        Tree documentTree = new Tree(); //TreeView
	Object[][] documentRoot = new Object[][]{new Object[] {currentMappe.getFp()}};
                
        documentTree.addItem(documentRoot[0][0]);
                
	appendDocuments(currentMappe, documentTree);//appendDocuments(currentMappe, documentRoot);

	documentBox.addComponent(documentTree);
	guiElemente.put("Dokumente", documentTree);

	return documentBox;
    }

    private VerticalLayout initVerweiseBaum() {
        VerticalLayout verweiseBox = new VerticalLayout();
	Tree verweiseTree = new Tree(); //TreeView
	Object[][] verweiseRoot = new Object[][]{new Object[] {"Verweise"}};
		
        verweiseTree.addItem(verweiseRoot[0][0]);

	for (String verweis : verweise) {
            verweiseTree.addItem(verweis);
            verweiseTree.setParent(verweis, verweiseRoot[0][0]);   //Hier nötig !!!!
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
	//inhalt.getStyleClass().add("bearbeitungsfeld");

	return inhalt;
    }

    private void showMappe() {
       	getBearbeitungsFeld().removeAllComponents();

	getBearbeitungsFeld().addComponent(new MappenView(currentMappe));
    }
    
    private VerticalLayout getBearbeitungsFeld() {
    	return (VerticalLayout) guiElemente.get("Bearbeitungsfeld");
    }

    private void initGUI() {
        BorderLayout borderLayout = new BorderLayout();
	HorizontalLayout splitPane = initSplitPane();
                
        GridLayout menuPane = new GridLayout(1,2);
	MenuBar menu = initMenu();
	HorizontalLayout toolbar = initToolbar();
	guiElemente.put("Toolbar", toolbar);
	menuPane.addComponent(menu, 0, 0);	
        menuPane.addComponent(toolbar, 0, 1);
        menuPane.setWidth("100%"); //CSS

	HorizontalLayout statusbar = initStatusbar();	//statusbar.prefWidthProperty().bind(scene.widthProperty());

        borderLayout.addComponent(menuPane, BorderLayout.Constraint.NORTH);
	borderLayout.addComponent(splitPane, BorderLayout.Constraint.CENTER);
	borderLayout.addComponent(statusbar, BorderLayout.Constraint.SOUTH);
		
        //borderLayout.prefHeightProperty().bind(scene.heightProperty());
	//borderLayout.prefWidthProperty().bind(scene.widthProperty());

        this.addComponent(borderLayout);

//	String css = "layout.css";
//	ObservableList<String> cssStyle = loadSkin(css);
//	scene.getStylesheets().clear();
//	scene.getStylesheets().addAll(cssStyle);
                
         showMappe();
    }

    private void initMappen(String parameter) {
        ArrayList<Mappe.VertragsMappe> vertragsMappen  = CommonGuiProblems.ladeMappen();
        if(parameter == null){
            //einfach die erst beste Mappe anzeigen
            try { 
                this.verweise.addAll(CommonGuiProblems.findeVerweise(vertragsMappen.get(0).getAzB()));
                this.currentMappe = vertragsMappen.get(0);
            } 
            catch (Exception e) {
		System.out.println(e.getMessage());
            } 
        }
        else{
            //Mappe mit übergebenen AzB heraussuchen
            this.verweise.addAll(CommonGuiProblems.findeVerweise(parameter));
            for(Mappe.VertragsMappe mappe : vertragsMappen){
                if(mappe.getAzB().equals(parameter)){
                    this.currentMappe = mappe;
                }
            }
        }
    }
    
    private void appendDocuments(Document doc, Tree tree) {
		for (Document children : doc.getChildren()) {
                    tree.addItem(children.getTitel());
                    tree.setParent(children.getTitel(), getRootItem(tree));   //Hier nötig !!!!
                    tree.setChildrenAllowed(children.getTitel(), false);
		}

	}

    private Object getRootItem(Tree tree) {
        Object rootItem = null;
        for(Object i : tree.rootItemIds()){
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void initVerweiseBaumController() {
       getVerweiseTree().setImmediate(true);
        
       getVerweiseTree().addValueChangeListener(new Property.ValueChangeListener() {

           @Override
           public void valueChange(Property.ValueChangeEvent event) {
               String selectedDocument =  getVerweiseTree().getValue().toString();
               ladeUndOeffneEntsprechendeMappe(selectedDocument);
           }
       });               //setOnMouseClicked(new EventHandler<Event>() {
			
    }

    private void initDocumentTreeController() {
        getDocumentTree().setImmediate(true);
        getDocumentTree().addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
               String selectedDocument =  getDocumentTree().getValue().toString(); //getSelectionModel().getSelectedItem()
		if (selectedDocument.contains(currentMappe.getTitel())){
					showMappe();
				}
				else{
					changeViewToDoc(currentMappe, selectedDocument);
				}
            }
            });
        }

    private void ladeUndOeffneEntsprechendeMappe(String selectedDocument) {
        VertragsMappenView neueVertragsMappenView = new VertragsMappenView(selectedDocument);
        MyApplication.tabsheet.addTab(neueVertragsMappenView).setCaption(neueVertragsMappenView.getTitle());
        MyApplication.tabsheet.getTab(neueVertragsMappenView).setClosable(true);
    }

    private Tree getVerweiseTree() {
        return ((Tree) guiElemente.get("Verweise"));
    }

    private Tree getDocumentTree() {
         return ((Tree) guiElemente.get("Dokumente"));
    }
    
    private void changeViewToDoc(Document document, String selectedDocument) {
         for(Document doc : document.getChildren()){
		if(selectedDocument.contains(doc.getTitel())){
			showDocument(doc);
			break;
		}
	}
    }
    
    public void showDocument(Document doc) {
	if (doc instanceof Vertragsblatt) {
		showVertragsblatt(doc);
	}
	else if (doc instanceof Vertrag) {
		showVertrag(doc);
	}
	else if (doc instanceof Auszahlung) {
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
	//toolbar.prefWidthProperty().bind(scene.widthProperty());
        String basepath = VaadinService.getCurrent().getBaseDirectory().getPath();
		
        FileResource imageDrop = new FileResource(new File(basepath+"/img/TbCopy.gif"));
        FileResource imagePrint = new FileResource(new File(basepath+"/img/TbPrint.gif"));
        FileResource imageOrg = new FileResource(new File(basepath+"/img/TbOriginal.gif"));
        FileResource imageGetOrg = new FileResource(new File(basepath+"/img/TbKopie.gif"));
        FileResource imageHelp = new FileResource(new File(basepath+"/img/TbHelp.gif"));
	
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
}
