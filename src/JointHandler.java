import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class JointHandler extends DefaultHandler {

	private ArrayList<Frame> frames = new ArrayList<Frame>();
	private Frame currentFrame = null;
	private Joint currentJoint = null;
	
	private String tagValue;
	
	public ArrayList<Frame> getFrames()
	{
		return frames;
	}
	
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if(qName.equalsIgnoreCase("frame")) {
			currentFrame = new Frame(Integer.parseInt(attributes.getValue("no")));
			frames.add(currentFrame);
		} else if(qName.equalsIgnoreCase("joint")) {
			currentJoint = new Joint(attributes.getValue("type"));
			currentFrame.addJoint(currentJoint);
		}
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("x"))
		{
			currentJoint.setX(Double.parseDouble(tagValue.replace(",", ".")));
		}
		else if(qName.equalsIgnoreCase("y"))
		{
			currentJoint.setY(Double.parseDouble(tagValue.replace(",", ".")));
		}
		else if(qName.equalsIgnoreCase("z"))
		{
			currentJoint.setZ(Double.parseDouble(tagValue.replace(",", ".")));
		}
	}

	public void characters(char ch[], int start, int length) throws SAXException {
		tagValue = new String(ch, start, length);
	}
}
