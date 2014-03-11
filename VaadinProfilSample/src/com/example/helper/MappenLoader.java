package com.example.helper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.example.mappe.Auszahlung;
import com.example.mappe.Document;
import com.example.mappe.Vertrag;
import com.example.mappe.VertragsMappe;
import com.example.mappe.Vertragsblatt;
import com.example.mappe.Zuwendung;
import com.vaadin.server.VaadinService;

public class MappenLoader extends DefaultHandler {
	static final String neueZeile = System.getProperty("line.separator");

	static private Writer out = null;

	private VertragsMappe mappe;
	private String eName;
	private StringBuffer textBuffer = null;
	private Document document = null;
	private Document docInDoc = null;

	public VertragsMappe mappeLaden(VertragsMappe mappe, int number)
			throws IOException {

		this.mappe = mappe;

		SAXParser saxParser;

		try {
			saxParser = SAXParserFactory.newInstance().newSAXParser();
			String basepath = VaadinService.getCurrent().getBaseDirectory()
					.getAbsolutePath();
			File resource = new File(basepath + "/mappe"
					+ String.valueOf(number) + ".xml");
			saxParser.parse(resource, this);
		} catch (ParserConfigurationException pe) {
			pe.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}
		return mappe;
	}

	// Starttag auslesen
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attrs) throws SAXException {
		textPuffer();
		eName = ("".equals(localName)) ? qName : localName;

		// Erfassen der Attribute in den Starttags
		if (attrs != null) {
			for (int i = 0; i < attrs.getLength(); i++) {
				String aName = attrs.getLocalName(i);
				if ("".equals(aName))
					aName = attrs.getQName(i);
			}
		}
	}

	// Schlusstags auslesen
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		textPuffer();
		eName = ("".equals(localName)) ? qName : localName;
		if (eName.equals("Bezeichnung")) {
			mappe.getUnterDokumente().add(document);
		}
	}

	// Erzeugt einen String aus den Char-Arrays und liest
	// diesen in einen StringBuffer ein
	public void characters(char[] buf, int offset, int len) throws SAXException {
		String s = new String(buf, offset, len);
		if (s.contains("\t") || s.contains("\n")) {
			s = "\n";
		}

		else if (textBuffer == null)
			textBuffer = new StringBuffer(s);
		else
			textBuffer.append(s);
	}

	/** ************** Hilfsmethoden ******************* */

	// Wandelt den StringBuffer in einen String und
	private void textPuffer() throws SAXException {
		if (textBuffer == null)
			return;
		ausgabe(eName, textBuffer.toString());
		textBuffer = null;
	}

	private void createVorgang(String value) {
		if (value.equals("VNS-Vertrag")) {
			document = new Vertrag();
		}
		if (value.equals("Auszahlung"))
			document = new Auszahlung();
		document.setTitel(value);
	}

	private void createDocInVorgang() {
		if (document.getTitel().equals("VNS-Vertrag")) {

			docInDoc = new Vertragsblatt();
			document.getUnterDokumente().add(docInDoc);
		}

	}

	// Ausgabe des Strings
	private void ausgabe(String key, String value) throws SAXException {
		try {
			if (out == null)
				out = new OutputStreamWriter(System.out, "UTF8");
			if (key.equals("AktenzeichenA"))
				mappe.setAzA(value);
			if (key.equals("AktenzeichenB"))
				mappe.setAzB(value);
			if (key.equals("BNRZD"))
				mappe.setBnrzd(value);
			if (key.equals("Amt"))
				mappe.setAmt(value);
			if (key.equals("FP"))
				mappe.setFp(value);
			if (key.equals("EUC"))
				mappe.setEuCode(value);
			if (key.equals("Erstzahlungsjahr"))
				mappe.setErstzahlungsjahr(value);
			if (key.equals("Status"))
				mappe.setStatus(value);

			// Neuer Vorgang
			if (key.equals("Bezeichnung"))
				createVorgang(value);
			if (key.equals("VStatus")) {
				if (document instanceof Vertrag) {
					((Vertrag) document).setStatus(value);
				}
				if (document instanceof Auszahlung) {
					((Auszahlung) document).setStatus(value);
				}
			}
			if (key.equals("Zuwendungssumme")) {
				if (document instanceof Vertrag) {
					((Vertrag) document).setZuwendung(Float.parseFloat(value));
				}
				if (document instanceof Auszahlung) {
					((Auszahlung) document).setZuwendungssumme(Float
							.parseFloat(value));
				}
			}
			if (key.equals("Zahlungsbetrag")) {
				if (document instanceof Auszahlung) {
					((Auszahlung) document).setZahlungsbetrag(Float
							.parseFloat(value));
				}
			}

			// Vertragsblatt
			if (key.equals("Vertragsbeginn")) {
				createDocInVorgang();
				((Vertragsblatt) docInDoc).setVertragsbeginn(value);
			}
			if (key.equals("Vertragslaufzeit"))
				((Vertragsblatt) docInDoc).setLaufzeit(Integer
						.parseInt(value));
			if (key.equals("Vertragsabschluss"))
				((Vertragsblatt) docInDoc).setVertragsabschluss(value);

			if (key.equals("BezugsJahr")) {
				((Vertragsblatt) docInDoc).getZuwendungen().add(
						new Zuwendung());
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setBezugsJahr(Integer.parseInt(value));
			}
			if (key.equals("PC"))
				((Vertragsblatt) docInDoc).getLastZuwendung().setPC(
						Integer.parseInt(value));
			if (key.equals("Beihilfesatz"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setBeihilfesatz(Float.parseFloat(value));
			if (key.equals("Vertragsflaeche"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setVertragsflaeche(Float.parseFloat(value));
			if (key.equals("Zuwendungsbetrag"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setZuwendungsbetrag(Float.parseFloat(value));
			if (key.equals("EU-ABB"))
				((Vertragsblatt) docInDoc).getLastZuwendung().setEUABB(
						value);
			if (key.equals("EU-Titel"))
				((Vertragsblatt) docInDoc).getLastZuwendung().setEUTitel(
						value);
			if (key.equals("Anteil-EU"))
				((Vertragsblatt) docInDoc).getLastZuwendung().setAnteilEU(
						Float.parseFloat(value));
			if (key.equals("Landestitel"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setLandestitel(value);
			if (key.equals("Anteil-Land"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setAnteilLand(Float.parseFloat(value));
			if (key.equals("Titel-Sonst"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setTitelSonst(value);
			if (key.equals("Anteil-Sonst"))
				((Vertragsblatt) docInDoc).getLastZuwendung()
						.setAnteilSonst(Float.parseFloat(value));

			eName = "";
			out.flush();
		} catch (IOException ex) {
			throw new SAXException("Ein-/Ausgabefehler", ex);
		}
	}

}