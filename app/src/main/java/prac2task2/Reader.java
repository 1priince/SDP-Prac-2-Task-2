package prac2task2;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Reader {
    public Reader() {}
    public void readAndPrint(String path) {
        try{
            File file = new File(path);     
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    System.out.println(node.getNodeName() + ": " + node.getTextContent());
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
