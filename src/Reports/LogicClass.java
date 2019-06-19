package Reports;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

class LogicClass
{
   public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
   {
       File fXmlFile = new File("BMIReportConfig.xml");
       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
       Document doc = dBuilder.parse(fXmlFile);
       doc.getDocumentElement().normalize();
       return doc.getElementsByTagName(nodeName).item(0).getTextContent();
   }
}
