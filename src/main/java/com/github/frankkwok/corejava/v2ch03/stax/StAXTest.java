package com.github.frankkwok.corejava.v2ch03.stax;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class StAXTest {
    public static void main(String[] args) throws IOException, XMLStreamException {
        String url;
        if (args.length > 0) {
            url = args[0];
        } else {
            url = "http://www.w3c.org";
        }
        System.out.println("Using " + url);

        InputStream in = new URL(url).openStream();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(in);
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equals("a")) {
                    String href = reader.getAttributeValue(null, "href");
                    if (href != null) {
                        System.out.println(href);
                    }
                }
            }
        }
    }
}
