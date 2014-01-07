package com.example.helper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.vaadin.server.VaadinService;

public class StringLoader extends DefaultHandler {

	static final String neueZeile = System.getProperty("line.separator");

	static private Writer out = null;

	private String key;

	private StringBuffer textBuffer = null;

	private HashMap<String, String> map = new HashMap<String, String>();

	public HashMap<String, String> ladeStrings() throws IOException {
		SAXParser saxParser;

		try {
			saxParser = SAXParserFactory.newInstance().newSAXParser();
			String basepath = VaadinService.getCurrent().getBaseDirectory()
					.getAbsolutePath();
			File resource = new File(basepath + "/Strings.xml");
			saxParser.parse(resource, this);
		} catch (ParserConfigurationException pe) {
			pe.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		}
		return map;
	}

	// Starttag auslesen
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attrs) throws SAXException {
		textPuffer();
		key = ("".equals(localName)) ? qName : localName;

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
		key = ("".equals(localName)) ? qName : localName;
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
		ausgabe(key, textBuffer.toString());
		textBuffer = null;
	}

	// Ausgabe des Strings
	private void ausgabe(String key, String value) throws SAXException {
		try {
			if (out == null)
				out = new OutputStreamWriter(System.out, "UTF8");

			map.put(key, value);

			key = "";
			out.flush();
		} catch (IOException ex) {
			throw new SAXException("Ein-/Ausgabefehler", ex);
		}
	}

}
