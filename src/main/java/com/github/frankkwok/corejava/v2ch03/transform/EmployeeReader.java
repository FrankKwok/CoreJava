package com.github.frankkwok.corejava.v2ch03.transform;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Frank Kwok on 2017/4/27.
 */
public class EmployeeReader implements XMLReader {
    private ContentHandler handler;

    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return false;
    }

    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException, SAXNotSupportedException {
        return null;
    }

    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException, SAXNotSupportedException {

    }

    @Override
    public void setEntityResolver(EntityResolver resolver) {

    }

    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    @Override
    public void setDTDHandler(DTDHandler handler) {

    }

    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    @Override
    public void setContentHandler(ContentHandler handler) {
        this.handler = handler;
    }

    @Override
    public ContentHandler getContentHandler() {
        return handler;
    }

    @Override
    public void setErrorHandler(ErrorHandler handler) {

    }

    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

    @Override
    public void parse(InputSource input) throws IOException, SAXException {
        BufferedReader in = new BufferedReader(new InputStreamReader(input.getByteStream()));
        String rootElement = "staff";

        AttributesImpl attributes = new AttributesImpl();
        if (handler == null) {
            throw new SAXException("No content handler");
        }

        handler.startDocument();
        handler.startElement("", rootElement, rootElement, attributes);
        String line;
        while ((line = in.readLine()) != null) {
            handler.startElement("", "employee", "employee", attributes);
            StringTokenizer t = new StringTokenizer(line, "|");

            handler.startElement("", "name", "name", attributes);
            String s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "name", "name");

            handler.startElement("", "salary", "salary", attributes);
            s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "salary", "salary");

            attributes.addAttribute("", "year", "year", "CDATA", t.nextToken());
            attributes.addAttribute("", "month", "month", "CDATA", t.nextToken());
            attributes.addAttribute("", "day", "day", "CDATA", t.nextToken());
            handler.startElement("", "hiredate", "hiredate", attributes);
            handler.endElement("", "hiredate", "hiredate");
            attributes.clear();

            handler.endElement("", "employee", "employee");
        }

        handler.endElement("", rootElement, rootElement);
        handler.endDocument();
    }

    @Override
    public void parse(String systemId) throws IOException, SAXException {

    }
}
