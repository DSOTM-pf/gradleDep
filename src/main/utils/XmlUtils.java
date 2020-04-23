package main.utils;

import main.entry.Dependency;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

public class XmlUtils {
    /*
     * 解析XML文件
     * */
    public  int getRepoTV(Dependency dependency) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(dependency.getManifestPath()));
        Element e = document.getDocumentElement();
        int TV = 0;
        NodeList list = document.getElementsByTagName("manifest");
        Element manifestElement = (Element) list.item(0);
        NodeList uses_sdkList = manifestElement.getElementsByTagName("uses-sdk");
        Element uses_sdk = (Element)uses_sdkList.item(0);
        if(uses_sdk.getAttribute("android:targetSdkVersion")!="")
        {
        TV = new Integer(uses_sdk.getAttribute("android:targetSdkVersion"));
        }
        //new Integer(manifestElement.getElementsByTagName("uses-sdk").item(0).getTextContent());

        return TV;
    }

    public boolean findMaxsdkVersion(Dependency dependency) throws IOException {
        File file = new File(dependency.getManifestPath());
        InputStreamReader read = new InputStreamReader(new FileInputStream(file),"UTF-8");//考虑到编码格式
        BufferedReader bufferedReader = new BufferedReader(read);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            if(line.startsWith("#")){
                continue;
            }
            //指定字符串判断处
            if (line.contains("android:maxsdkversion")) {
                System.out.println(line);
                return true;
            }
        }
        return false;
    }
    /*
     * 解析XML文件
     * */
}
