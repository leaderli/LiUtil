package com.leaderli.lidomutil.util;

import com.leaderli.liutil.dom.LiDomUtil;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.junit.Test;

public class LiDomUtilTest {


    @Test
    public void test() throws DocumentException {
        DOMDocument dom = LiDomUtil.getDOMDocumentByPath("/test1.xml");
        LiDomUtil.prettyPrint(dom);
        LiDomUtil.prettyPrint(dom);
        for (DOMElement child : LiDomUtil.getChildren(dom)) {
            LiDomUtil.prettyPrint(child);
        }
        System.out.println("-------------------->");

        DOMElement root = LiDomUtil.getDOMRootByPath("/test1.xml");
        for (DOMElement child : LiDomUtil.getChildren(root)) {
            LiDomUtil.prettyPrint(child);
        }

    }

}
