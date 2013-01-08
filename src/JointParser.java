import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class JointParser {

	SAXParserFactory factory = SAXParserFactory.newInstance();
	JointHandler handler;
	
	public JointParser(String file)
	{
		handler = new JointHandler();
		try {
			
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File(file), handler);
			
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public JointHandler getHandler() {
		return handler;
	}
}
