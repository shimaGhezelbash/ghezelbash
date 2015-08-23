/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author shima
 */
public class JmHtmlReplasor {

    /**
     * برای ایجاد تغییرات در اچ تی ام ال های خوانده شده از فایل این متد فایل اچ
     * تی ام ال را خوانده و مقدار مورد نظر را در اتریبیوت آن کپی میکند
     *
     * @param html
     * @param selector
     * @param attr
     * @param value
     */
    public static void setAttr(StringBuilder html, String selector, String attr, String value) {
        int index = html.indexOf("\""+selector+"\"");//finding div in wich id="sw"
        if (index > -1) {
            index = html.indexOf(attr, index); //finding end of "sw" div ( <div id="sw" clas="example" style="any" >     
        html = html.insert(index + attr.length()+2,value);

        }
    }
    public static void main(String[] args) {
        StringBuilder myHtml = new StringBuilder("<table>\n" +
"                        \n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <label class=\"ltr\">نام : </label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input id=\"i1\" type=\"text\" value=\"\" readonly=\"readonly\"  class=\"fit\" />\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <label class=\"ltr\">نام خانوادگی : </label>\n" +
"                        </td>\n" +
"                        <td>\n" +
"                            <input type=\"text\" value=\" باقرنيا\" readonly=\"readonly\"  class=\"fit\" />\n" +
"                        </td>");
        
        
        setAttr(myHtml, "i1", "value", "salam");
        System.out.println(myHtml);
        
    }

}
