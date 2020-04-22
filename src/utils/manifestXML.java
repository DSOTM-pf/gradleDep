package utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class manifestXML {
    /*
    * 解析XML文件
    * */
    public static int getRepoTV(String Path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("/Users/***/exam.xml"));
        Element e = document.getDocumentElement();
        int TV = 0;
        NodeList list = document.getElementsByTagName("manifest");
        Element manifestElement = (Element) list.item(0);
        NodeList uses_sdkList = manifestElement.getElementsByTagName("uses-sdk");
        Element uses_sdk = (Element)uses_sdkList.item(0);

        TV = new Integer(uses_sdk.getAttribute("android:targetSdkVersion"));
    //new Integer(manifestElement.getElementsByTagName("uses-sdk").item(0).getTextContent());

        return TV;
    }
    /*
    * 解析XML文件
    * */
}
