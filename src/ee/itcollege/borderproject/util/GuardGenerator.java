package ee.itcollege.borderproject.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ee.itcollege.borderproject.model.Guard;

public class GuardGenerator {

	private static final String SERVICE_URL_FORMAT = 
			"http://namegenerator.juergenbouche.de/generate.php?c=230&g=male&n=%s&m=xml";
	private static final String PERSON_TAG = "person";
	
	private static final int NAME_LIMIT_PER_RESPONSE = 10;
	private static final int GUARD_MAX_AGE = 70;
	private static final int GUARD_MIN_AGE = 19;
	
	
	public List<Guard> generate(int count) {
		List<Guard> randomGuards = new ArrayList<Guard>();
		Random random = new Random();
		
		for (String name : getRandomNames(count)) {
			Guard guard = new Guard();
			guard.setName(name);
			guard.setAge(getRandomAge(random));
			
			randomGuards.add(guard);
		}
		
		return randomGuards;
	}
	
	private int getRandomAge(Random random) {
		return random.nextInt(GUARD_MAX_AGE - GUARD_MIN_AGE + 1) + GUARD_MIN_AGE;
	}
	
	private List<String> getRandomNames(int count) {
		List<String> randomNames = new ArrayList<String>();
		
		try {		
			for (Document document : getResponseXmlDocuments(count)) {
				
				Element root = document.getDocumentElement();
	            NodeList itemList = root.getElementsByTagName(PERSON_TAG);
	            
	            for (int personIndex = 0; personIndex < itemList.getLength(); personIndex++) {
	            	Node item = itemList.item(personIndex);
	            	randomNames.add(item.getFirstChild().getFirstChild().getNodeValue());
	            }
			}        
		} 
		catch (Exception e) {
			randomNames.clear();
			randomNames.addAll(getPreDefinedNames(count));
		}

		return randomNames;
	}
	
	private List<String> getPreDefinedNames(int count) {
		List<String> preDefinedNames = new ArrayList<String>();
		List<String> names = getSomeNames();
		
		while (count > 0) {
			for (String name : names) {
				preDefinedNames.add(name);
				count--;
				if (count == 0)
					break;
			}
		}
		
		return preDefinedNames;
	}
	
	private List<String> getSomeNames() {
		List<String> names = new ArrayList<String>();
		names.add("Peeter");
		names.add("Mihkel");
		names.add("Sander");
		names.add("Silver");
		
		return names;
	}
	
	private List<Document> getResponseXmlDocuments(int count) throws Exception {
		List<Document> documents = new ArrayList<Document>();		
		
		while(count > 0) {
			Document document;			
			
			if (count >= NAME_LIMIT_PER_RESPONSE) {
				document = getResponseDocument(NAME_LIMIT_PER_RESPONSE);
				count -= NAME_LIMIT_PER_RESPONSE;
			} 
			else {
				document = getResponseDocument(count);
				count -= count;
			}			
			
			documents.add(document);
		}		
		
		return documents;
	}
	
	private Document getResponseDocument(int count) throws Exception {
		URL serviceUrl = new URL(String.format(SERVICE_URL_FORMAT, count));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(serviceUrl.openStream());
	}
}
