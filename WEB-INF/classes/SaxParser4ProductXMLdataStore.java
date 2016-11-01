import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.concurrent.CopyOnWriteArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser4ProductXMLdataStore extends DefaultHandler {
	static Map<String, List> m = new HashMap<String, List>();
	Product p;
	List<Product> plist = new CopyOnWriteArrayList<Product>();
	String consoleXmlFileName;
	String elementValueRead;

	public SaxParser4ProductXMLdataStore(String consoleXmlFileName){
		this.consoleXmlFileName = consoleXmlFileName;
		parseDocument();
		//prettyPrint();
	}
	
	public List<Product> getProducts(){
		return plist;
	}

	private void parseDocument() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			parser.parse(consoleXmlFileName, this);
		} catch (ParserConfigurationException e) {
			System.out.println("ParserConfig error");
		} catch (SAXException e) {
			System.out.println("SAXException : xml not well formed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void prettyPrint() {
		for (Product p : plist) {
			System.out.println("Product #" + p.id + ":");
			System.out.println("\t\t retailer: " + p.retailer);
			System.out.println("\t\t name: " + p.name);
			System.out.println("\t\t price: " + p.price);
			System.out.println("\t\t accessory: ");
			for(String s:p.accessories){
				System.out.println("\t\t\t\t"+s);
			}
		}
	}
	
	@Override
	public void startElement(String str1, String str2, String elementName,
			Attributes attributes) throws SAXException {
		if (elementName.equalsIgnoreCase("TV")) {
			p = new TV();
			p.setId(attributes.getValue(0));
			p.setRetailer(attributes.getValue(1));
            p.setZip(attributes.getValue(2));
			p.setState(attributes.getValue(3));
            p.setCity(attributes.getValue(4));
		}
		if (elementName.equalsIgnoreCase("Laptop")) {
			p = new Laptop();
			p.setId(attributes.getValue(0));
			p.setRetailer(attributes.getValue(1));
            p.setZip(attributes.getValue(2));
			p.setState(attributes.getValue(3));
            p.setCity(attributes.getValue(4));
		}
		if (elementName.equalsIgnoreCase("SmartPhone")) {
			p = new SmartPhone();
			p.setId(attributes.getValue(0));
			p.setRetailer(attributes.getValue(1));
            p.setZip(attributes.getValue(2));
			p.setState(attributes.getValue(3));
            p.setCity(attributes.getValue(4));
		}
		if (elementName.equalsIgnoreCase("Tablet")) {
			p = new Tablet();
			p.setId(attributes.getValue(0));
			p.setRetailer(attributes.getValue(1));
            p.setZip(attributes.getValue(2));
			p.setState(attributes.getValue(3));
            p.setCity(attributes.getValue(4));
		}
	}

	@Override
	public void endElement(String str1, String str2, String element)
			throws SAXException {
		if (element.equalsIgnoreCase("image")) {
			p.setImage(elementValueRead);
		}
		if (element.equalsIgnoreCase("name")) {
			p.setName(elementValueRead);
		}		
		if (element.equalsIgnoreCase("condition")) {
			p.setCondition(elementValueRead);
		}
		if (element.equalsIgnoreCase("price")) {
			p.setPrice(Integer.parseInt(elementValueRead));
		}
		if(element.equalsIgnoreCase("accessory")){
           p.getAccessories().add(elementValueRead);
        }
		if (element.equals("TV") || element.equals("Laptop")
				|| element.equals("SmartPhone") || element.equals("Tablet")) {
			plist.add(p);
		}
	}

	@Override
	public void characters(char[] content, int begin, int end)
			throws SAXException {
		elementValueRead = new String(content, begin, end);
	}
	
	public static void loadProducts(){
		m.put("TV", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductTVCatalog.xml").getProducts());
		m.put("Laptop", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductLaptopCatalog.xml").getProducts());
		m.put("SmartPhone", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductSmartPhoneCatalog.xml").getProducts());
		m.put("Tablet", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductTabletCatalog.xml").getProducts());
	}

	public static void main(String[] args) {
		loadProducts();
	}

}