package prac2task2;
import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import com.google.gson.JsonObject;

public class Reader {
    public Reader() {}
    public void readAndPrint(String path, boolean printName, boolean printPostalZip, boolean printRegion, boolean printCountry, boolean printAddress) {
        try{
            File file = new File(path);     
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            Node node;

            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    JsonObject jsonObject = new JsonObject();
                    if (printName && "name".equals(node.getNodeName())) {
                        String name = element.getTextContent();
                        jsonObject.addProperty("Name", name);
                    }
                    if (printPostalZip && "postalZip".equals(node.getNodeName())) {
                        String postalZip = element.getTextContent();
                        jsonObject.addProperty("Postal/Zip", postalZip);
                    }
                    if (printRegion && "region".equals(node.getNodeName())) {
                        String region = element.getTextContent();
                        jsonObject.addProperty("Region", region);
                    }
                    if (printCountry && "country".equals(node.getNodeName())) {
                        String country = element.getTextContent();
                        jsonObject.addProperty("Country", country);
                    }
                    if (printAddress && "address".equals(node.getNodeName())) {
                        String address = element.getTextContent();
                        jsonObject.addProperty("Address", address);
                    }
                    System.out.println(jsonObject.toString());
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
