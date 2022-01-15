package com.leaderli.lidomutil.util;

import com.leaderli.liutil.dom.LiDomUtil;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMElement;
import org.junit.Assert;
import org.junit.Test;

public class LiDomUtilTest extends Assert {


    private static class Visitor {
        void visit(DOMElement dom) {

        }

        void visit(String text) {

        }
    }

    private static class MyDom {

        private final DOMElement dom;

        public MyDom(DOMElement dom) {
            this.dom = dom;
        }

        void accept(Visitor visitor) {

            visitor.visit(dom);
            LiDomUtil.getChildren(dom).forEach(child-> new MyDom(child).accept(visitor));
            visitor.visit(dom.getTextTrim());
        }
    }

    @Test
    public void test() throws DocumentException {
        DOMElement dom = LiDomUtil.getDOMRootByPath("/test1.xml");

        assertEquals(dom.asXML(),"<test>\n" +
                "    <t1>1</t1>\n" +
                "    <t2>1</t2>\n" +
                "    <t3>\n" +
                "        <tt3>tt3</tt3>\n" +
                "    </t3>\n" +
                "</test>");


        MyDom myDom = new MyDom(dom);
        myDom.accept(new Visitor(){
            @Override
            void visit(DOMElement dom) {
            }

            @Override
            void visit(String text) {
            }
        });

    }

}
