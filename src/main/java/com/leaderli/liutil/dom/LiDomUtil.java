package com.leaderli.liutil.dom;

import com.sun.org.apache.xerces.internal.impl.Constants;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

@SuppressWarnings("unchecked")
public class LiDomUtil {

    /**
     * 在读取文件时，去掉dtd的验证，可以缩短运行时间
     */
    public static SAXReader getSAXReader() {
        SAXReader saxReader = new SAXReader(DOMDocumentFactory.getInstance(), false);
        try {
            saxReader.setFeature(Constants.XERCES_FEATURE_PREFIX + Constants.LOAD_EXTERNAL_DTD_FEATURE, false);  //设置不需要校验头文件
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saxReader;
    }

    public static DOMDocument getDOMDocumentByPath(String path) throws DocumentException {
        return (DOMDocument) getSAXReader().read(LiDomUtil.class.getResourceAsStream(path));

    }

    public static DOMElement getDOMRootByPath(String path) throws DocumentException {
        return (DOMElement) getDOMDocumentByPath(path).getRootElement();

    }

    public static DOMDocument getDOMDocumentByString(String xml) throws DocumentException {
        return (DOMDocument) getSAXReader().read(new StringReader(xml));

    }

    public static DOMElement getDOMRootByString(String xml) throws DocumentException {
        return (DOMElement) getDOMDocumentByString(xml).getRootElement();

    }

    public static List<DOMElement> getChildren(Node element) {

        return element.selectNodes("child::*");
    }

    public static List<DOMElement> getChildren(Node element, String xpath) {

        return element.selectNodes(xpath);
    }

    /**
     * 打印格式化文本
     */
    public static void prettyPrint(Node node) {
        try {
            //document
            StringWriter writer = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
            xmlWriter.write(node);
            xmlWriter.close();
            System.out.println(writer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 打印格式化文本
     */
    public static void createCompactFormat(Node node) {
        try {
            //document
            StringWriter writer = new StringWriter();
            XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createCompactFormat());
            xmlWriter.write(node);
            xmlWriter.close();
            System.out.println(writer.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
