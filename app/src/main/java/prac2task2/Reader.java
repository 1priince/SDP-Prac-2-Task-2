package prac2task2;
import java.io.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*;
import com.google.gson.JsonObject;

public class Reader {
    public Reader() {}
    public void readAndPrint(String path, boolean printName, boolean printPostalZip, boolean printRegion, boolean printCountry, boolean printAddress) {
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(new MyHandler(printName, printPostalZip, printRegion, printCountry, printAddress));
            xmlReader.parse(new InputSource(new FileInputStream(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyHandler extends DefaultHandler {
        private boolean printName;
        private boolean printPostalZip;
        private boolean printRegion;
        private boolean printCountry;
        private boolean printAddress;

        private StringBuilder currentValue;
        private JsonObject jsonObject;

        public MyHandler(boolean printName, boolean printPostalZip, boolean printRegion, boolean printCountry, boolean printAddress) {
            this.printName = printName;
            this.printPostalZip = printPostalZip;
            this.printRegion = printRegion;
            this.printCountry = printCountry;
            this.printAddress = printAddress;
            this.currentValue = new StringBuilder();
            this.jsonObject = new JsonObject();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentValue.setLength(0);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (printName && qName.equals("name")) {
                jsonObject.addProperty("Name", currentValue.toString());
            } else if (printPostalZip && qName.equals("postalZip")) {
                jsonObject.addProperty("Postal/Zip", currentValue.toString());
            } else if (printRegion && qName.equals("region")) {
                jsonObject.addProperty("Region", currentValue.toString());
            } else if (printCountry && qName.equals("country")) {
                jsonObject.addProperty("Country", currentValue.toString());
            } else if (printAddress && qName.equals("address")) {
                jsonObject.addProperty("Address", currentValue.toString());
            }
            if (!jsonObject.entrySet().isEmpty()) {
                System.out.println(jsonObject.toString());
                jsonObject = new JsonObject();
            }
        }
    }
}