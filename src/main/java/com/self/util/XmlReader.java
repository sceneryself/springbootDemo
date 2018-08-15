package com.self.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;

public class XmlReader {
    private static Logger log = LoggerFactory.getLogger("XmlReader");

    public static String getStrVal(String xmlContent, String xPath) {
        Node node = getFirstNode(xmlContent, xPath);
        return node != null ? node.getTextContent() : null;
    }

    public static Node getFirstNode(String xmlContent, String xPath) {
        NodeList nodeList = getNodes(xmlContent, xPath);
        return (nodeList != null && nodeList.getLength() > 0) ? nodeList.item(0) : null;
    }

    public static NodeList getNodes(String xmlContent, String xpath) {
        try {
            DocumentBuilderFactory docfactory = DocumentBuilderFactory.newInstance();
            docfactory.setNamespaceAware(true); // never forget this!
            DocumentBuilder builder = docfactory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(xmlContent.getBytes("UTF-8")));
            XPathExpression expr = XPathFactory.newInstance().newXPath().compile(xpath);
            NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            if (nodeList.getLength() == 0) {
                return null;
            }
            return nodeList;
        } catch (Exception e) {
            log.error("Failed to getNodes(), message=" + e.getMessage());
        }
        return null;
    }

}
