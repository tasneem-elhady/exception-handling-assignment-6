import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
public class WriteArxml {
    
    public static void main(String[] args)
            throws ParserConfigurationException, TransformerException , NotVaildAutosarFileException {
                ArxmlFile f = new ArxmlFile();
                String fileName = args[0];
                f.readFile(fileName);
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
                // root element
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("AUTOSAR");
                doc.appendChild(rootElement);

                // add Arxml elements
                for (int i = 0; i < f.fileContent.size(); i++)
                {
                    Container c = f.fileContent.get(i);
                    Element CONTAINER = doc.createElement("CONTAINER");
                    // add CONTAINER to root
                    rootElement.appendChild(CONTAINER);
                    // add Arxml attribute
                    CONTAINER.setAttribute("UUID", c.UUID);

                    Element SHORT_N = doc.createElement("SHORT-NAME");
                    SHORT_N.setTextContent(c.SHORT_NAME);
                    CONTAINER.appendChild(SHORT_N);
                    Element LONG_N = doc.createElement("LONG-NAME");
                    LONG_N.setTextContent(c.LONG_NAME);
                    CONTAINER.appendChild(LONG_N);
                }
                    File newfile = new File(fileName.substring(0, fileName.lastIndexOf(".")).concat("_mod.arxml"));
                    writeXml(doc, newfile ); 
            }
          
            // write doc to file
            private static void writeXml(Document doc,
                                         File f)
                    throws TransformerException {
          
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
          
                //adjust the format
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(f);
          
                transformer.transform(source, result);
          
            }
    }

