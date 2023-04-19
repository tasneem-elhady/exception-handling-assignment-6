import java.util.ArrayList;
import java.util.Collections;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.File;

public class ArxmlFile {
    public ArrayList<Container> fileContent;

    public void readFile(String fileName) throws NotVaildAutosarFileException{
        String extension = fileName.substring(fileName.lastIndexOf("."));
        //check extension
        if(!(extension.equals(".arxml")))
            throw new NotVaildAutosarFileException("Not an AUTOSAR file.");
        //check if file is empty
        else if(new File(fileName).length() == 0)
            throw new EmptyAutosarFileException("The file is empty.");
        else {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
            // optional, but recommended
            // process XML securely, avoid attacks like XML External Entities (XXE)
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            
                DocumentBuilder db = dbf.newDocumentBuilder();
                // parse ARXML file
                Document doc = db.parse(new File(fileName));
                //create nodelist of container element
                NodeList list1 = doc.getElementsByTagName("CONTAINER");
                fileContent = new ArrayList<Container>();
                //loop through the nodelist to get the attributes and elements below it
                for (int i = 0; i < list1.getLength(); i++) {
                    Node node = list1.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String UUID = element.getAttribute("UUID");
                        NodeList list2 = element.getElementsByTagName("LONG-NAME");
                        NodeList list3 = element.getElementsByTagName("SHORT-NAME");
                        String ln = list2.item(0).getTextContent();
                        String sn = list3.item(0).getTextContent();
                        fileContent.add(new Container(UUID, ln, sn));
                    }
                }
                //sort the containers alphabetically according to there SHORT_NAME
                Collections.sort(fileContent);
            }
            catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
