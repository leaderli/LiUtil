package com.leaderli.lidomutil.util;

import com.leaderli.liutil.dom.LiDomUtil;
import org.dom4j.DocumentException;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.junit.Test;

public class LiDomUtilTest {


    private static class Visitor {
        void visit(DOMElement dom) {

        }

        void visit(String text) {

        }
    }

    private static class MyDom {

        private DOMElement dom;

        public MyDom(DOMElement dom) {
            this.dom = dom;
        }

        void accept(Visitor visitor) {

            visitor.visit(dom);
            LiDomUtil.getChildren(dom).forEach(child->{
                new MyDom(child).accept(visitor);
            });
            visitor.visit(dom.getTextTrim());
        }
    }

    @Test
    public void test() throws DocumentException {
        DOMElement dom = LiDomUtil.getDOMRootByPath("/test1.xml");

        MyDom myDom = new MyDom(dom);
        myDom.accept(new Visitor(){
            @Override
            void visit(DOMElement dom) {
                System.out.println(dom.asXML());
                System.out.println("-----------------------------------");
            }

            @Override
            void visit(String text) {
                System.out.println(text);
                System.out.println("-----------------------------------text");
            }
        });

    }

}
