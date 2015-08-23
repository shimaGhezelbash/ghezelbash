package cms.cms;

import cms.tools.*;
import cms.access.*;
import java.util.HashMap;
import jj.*;
import java.util.List;
import java.util.Map;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;

public class Product {

    public static String tableName = "account_product";
    public static String _id = "id";
    public static String _page = "account_product_page";
    public static String _date = "account_product_date";
    public static String _name = "account_product_name";
    public static String _lang = "account_product_lang";//by mohammad
    public static String _parent = "account_product_parent";//by mohammad
    public static String _priority = "account_product_priority";//by mohammad
    public static String _visit = "account_product_visit";//by mohammad
    public static String _order = "account_product_order";//by mohammad
    public static String _rating = "account_product_rating";//by mohammad
    public static String _like = "account_product_like";//by mohammad
    public static String _dislike = "account_product_dislike";//by mohammad
    public static String _category_id = "account_product_category_id";//by mohammad
    public static String _pic1 = "account_product_pic1";
    public static String _pic2 = "account_product_pic2";
    public static String _pic3 = "account_product_pic3";
    public static String _pic4 = "account_product_pic4";
    public static String _pic5 = "account_product_pic5";
    public static String _pic6 = "account_product_pic6";
    public static String _pic_ext = "account_product_pic_ext";
    public static String _price1 = "account_product_price1";
    public static String _price2 = "account_product_price2";
    public static String _code = "account_product_code";
    public static String _prop1 = "account_product_prop1";
    public static String _val1 = "account_product_val1";
    public static String _prop2 = "account_product_prop2";
    public static String _val2 = "account_product_val2";
    public static String _prop3 = "account_product_prop3";
    public static String _val3 = "account_product_val3";
    public static String _prop4 = "account_product_prop4";
    public static String _val4 = "account_product_val4";
    public static String _prop5 = "account_product_prop5";
    public static String _val5 = "account_product_val5";
    public static String _prop6 = "account_product_prop6";
    public static String _val6 = "account_product_val6";
    public static String _prop7 = "account_product_prop7";
    public static String _val7 = "account_product_val7";
    public static String _prop8 = "account_product_prop8";
    public static String _val8 = "account_product_val8";
    public static String _prop9 = "account_product_prop9";
    public static String _val9 = "account_product_val9";
    public static String _prop10 = "account_product_prop10";
    public static String _val10 = "account_product_val10";
    public static String _prop11 = "account_product_prop11";
    public static String _val11 = "account_product_val11";
    public static String _prop12 = "account_product_prop12";
    public static String _val12 = "account_product_val12";
    public static String _prop13 = "account_product_prop13";
    public static String _val13 = "account_product_val13";
    public static String _prop14 = "account_product_prop14";
    public static String _val14 = "account_product_val14";
    public static String _prop15 = "account_product_prop15";
    public static String _val15 = "account_product_val15";
    public static String _prop16 = "account_product_prop16";
    public static String _val16 = "account_product_val16";
    public static String _prop17 = "account_product_prop17";
    public static String _val17 = "account_product_val17";
    public static String _prop18 = "account_product_prop18";
    public static String _val18 = "account_product_val18";
    public static String _prop19 = "account_product_prop19";
    public static String _val19 = "account_product_val19";
    public static String _prop20 = "account_product_prop20";
    public static String _val20 = "account_product_val20";
    public static String lbl_insert = "Ø°Ø®ÛŒØ±Ù‡";
    public static String lbl_delete = "Ø­Ø°Ù�";
    public static String lbl_edit = "ÙˆÛŒØ±Ø§ÛŒØ´";
    public static String lbl_add_en = "Ø§Ù�Ø²ÙˆØ¯Ù† Ø²Ø¨Ø§Ù† Ø§Ù†Ú¯Ù„ÛŒØ³ÛŒ";
    public static String lbl_edit_en = "ÙˆÛŒØ±Ø§ÛŒØ´ Ø¨Ø®Ø´ Ø§Ù†Ú¯Ù„ÛŒØ³ÛŒ";
    public static String lbl_add_ar = "Ø§Ù�Ø²ÙˆØ¯Ù† Ø²Ø¨Ø§Ù† Ø¹Ø±Ø¨ÛŒ";
    public static String lbl_edit_ar = "ÙˆÛŒØ±Ø§ÛŒØ´ Ø¨Ø®Ø´ Ø¹Ø±Ø¨ÛŒ";
    public static int rul_rfs = 56;
    public static int rul_ins = 57;
    public static int rul_edt = 58;
    public static int rul_dlt = 59;
    public static int rul_lng = 60;
    static int pageCounter = 4;
    //By Md
    public static String _abstract = "account_products_abstract";
    public static String _content = "account_products_content";

    public static String refresh(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_rfs);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            StringBuffer html = new StringBuffer();
            DefaultTableModel dtm = db.Select(tableName, _lang + "=1");
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
            html.append("<table class='show' id='refreshProduct' class='tahoma10' style='direction: rtl;width:982px'><thead>");
            html.append("<th class='show' width='5%'>id</th>");
            html.append("<th class='show' width='20%'>کد محصول</th>");
            html.append("<th class='show' width='60%'>نام محصول</th>");
            html.append("<th class='show' width='10%'>قیمت محصول</th>");
            html.append("<th class='show' width='10%'>گروه محصول</th>");
            html.append("<th class='show' width='5%'>تنظیمات</th>");
            html.append("</thead><tbody>");
            for (int i = 0; i < row.size(); i++) {
                html.append("<tr class='show'  onclick='cmsProduct.m_select(" + row.get(i).get(_id) + ");' class='mousePointer'>");
                html.append("<td class='show' class='c'>" + (row.get(i).get(_id).toString()) + "</td>");
                html.append("<td class='show' class='c'>" + (row.get(i).get(_code).toString()) + "</td>");
                html.append("<td class='show' class='r'>" + (row.get(i).get(_name).toString()) + "</td>");
                html.append("<td class='show' class='l'>" + (row.get(i).get(_price2).toString()) + "</td>");//By Md             
                html.append("<td class='show' class='r'>/" + row.get(i).get(_category_id).toString() + "/</td>");
                html.append("<td class='show' class='c'><img src='img/l.png' style='height:30px' /></td>");
                html.append("</tr>");
            }
            html.append("</tbody></table>");
            String height = jjTools.getParameter(request, "height");
            String panel = jjTools.getParameter(request, "panel");
            if (!jjNumber.isDigit(height)) {
                height = "400";
            }
            if (panel.equals("")) {
                panel = "swProductTbl";
            }
//            String html2 = Js.setHtml("#" + panel, html.toString());
//            html2 += Js.table("#refreshProduct", height, 0, Access_User.getAccessDialog(request, db, rul_ins).equals("") ? "14" : "", "Ù„ÛŒØ³Øª Ù…Ø­ØµÙˆÙ„Ø§Øª");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Product_button", "<input type=\"button\" id=\"insert_Product_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Product_new", Js.jjProduct.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();

//            map.put(_birth, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _birth), true));
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_page, jjTools.getParameter(request, _page));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_pic6, jjTools.getParameter(request, _pic6));
            map.put(_pic_ext, jjTools.getParameter(request, _pic_ext));
            map.put(_prop1, jjTools.getParameter(request, _prop1));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_prop2, jjTools.getParameter(request, _prop2));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_prop3, jjTools.getParameter(request, _prop3));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_prop4, jjTools.getParameter(request, _prop4));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_prop5, jjTools.getParameter(request, _prop5));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_prop6, jjTools.getParameter(request, _prop6));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_prop7, jjTools.getParameter(request, _prop7));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_prop8, jjTools.getParameter(request, _prop8));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_prop9, jjTools.getParameter(request, _prop9));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_prop10, jjTools.getParameter(request, _prop10));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_prop11, jjTools.getParameter(request, _prop11));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_prop12, jjTools.getParameter(request, _prop12));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_prop13, jjTools.getParameter(request, _prop13));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_prop14, jjTools.getParameter(request, _prop14));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_prop15, jjTools.getParameter(request, _prop15));
            map.put(_val15, jjTools.getParameter(request, _val15));
            map.put(_prop16, jjTools.getParameter(request, _prop16));
            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_prop17, jjTools.getParameter(request, _prop17));
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_prop18, jjTools.getParameter(request, _prop18));
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_prop19, jjTools.getParameter(request, _prop19));
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_prop20, jjTools.getParameter(request, _prop20));
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);
            map.put(_price1, jjNumber.isDigit(jjTools.getParameter(request, _price1)) ? Integer.parseInt(jjTools.getParameter(request, _price1)) : 0);
            map.put(_price2, jjNumber.isDigit(jjTools.getParameter(request, _price2)) ? Integer.parseInt(jjTools.getParameter(request, _price2)) : 0);
            //By Md
            map.put(_abstract, jjTools.getParameter(request, _abstract));
            map.put(_content, jjTools.getParameter(request, _content));

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 1);

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);
            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "Ø¹Ù…Ù„ÛŒØ§Øª Ø¯Ø±Ø¬ Ø¨Ù‡ Ø¯Ø±Ø³ØªÛŒ ØµÙˆØ±Øª Ù†Ú¯Ø±Ù�Øª.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjProduct.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String edit(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_edt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(_code, jjTools.getParameter(request, _code));
            map.put(_name, jjTools.getParameter(request, _name));
            map.put(_page, jjTools.getParameter(request, _page));
            map.put(_pic1, jjTools.getParameter(request, _pic1));
            map.put(_pic2, jjTools.getParameter(request, _pic2));
            map.put(_pic3, jjTools.getParameter(request, _pic3));
            map.put(_pic4, jjTools.getParameter(request, _pic4));
            map.put(_pic5, jjTools.getParameter(request, _pic5));
            map.put(_pic6, jjTools.getParameter(request, _pic6));
            map.put(_pic_ext, jjTools.getParameter(request, _pic_ext));
            map.put(_prop1, jjTools.getParameter(request, _prop1));
            map.put(_val1, jjTools.getParameter(request, _val1));
            map.put(_prop2, jjTools.getParameter(request, _prop2));
            map.put(_val2, jjTools.getParameter(request, _val2));
            map.put(_prop3, jjTools.getParameter(request, _prop3));
            map.put(_val3, jjTools.getParameter(request, _val3));
            map.put(_prop4, jjTools.getParameter(request, _prop4));
            map.put(_val4, jjTools.getParameter(request, _val4));
            map.put(_prop5, jjTools.getParameter(request, _prop5));
            map.put(_val5, jjTools.getParameter(request, _val5));
            map.put(_prop6, jjTools.getParameter(request, _prop6));
            map.put(_val6, jjTools.getParameter(request, _val6));
            map.put(_prop7, jjTools.getParameter(request, _prop7));
            map.put(_val7, jjTools.getParameter(request, _val7));
            map.put(_prop8, jjTools.getParameter(request, _prop8));
            map.put(_val8, jjTools.getParameter(request, _val8));
            map.put(_prop9, jjTools.getParameter(request, _prop9));
            map.put(_val9, jjTools.getParameter(request, _val9));
            map.put(_prop10, jjTools.getParameter(request, _prop10));
            map.put(_val10, jjTools.getParameter(request, _val10));
            map.put(_prop11, jjTools.getParameter(request, _prop11));
            map.put(_val11, jjTools.getParameter(request, _val11));
            map.put(_prop12, jjTools.getParameter(request, _prop12));
            map.put(_val12, jjTools.getParameter(request, _val12));
            map.put(_prop13, jjTools.getParameter(request, _prop13));
            map.put(_val13, jjTools.getParameter(request, _val13));
            map.put(_prop14, jjTools.getParameter(request, _prop14));
            map.put(_val14, jjTools.getParameter(request, _val14));
            map.put(_prop15, jjTools.getParameter(request, _prop15));
            map.put(_val15, jjTools.getParameter(request, _val15));
            map.put(_prop16, jjTools.getParameter(request, _prop16));
            map.put(_val16, jjNumber.isDigit(jjTools.getParameter(request, _val16)) ? Integer.parseInt(jjTools.getParameter(request, _val16)) : 0);
            map.put(_prop17, jjTools.getParameter(request, _prop17));
            map.put(_val17, jjNumber.isDigit(jjTools.getParameter(request, _val17)) ? Integer.parseInt(jjTools.getParameter(request, _val17)) : 0);
            map.put(_prop18, jjTools.getParameter(request, _prop18));
            map.put(_val18, jjNumber.isDigit(jjTools.getParameter(request, _val18)) ? Integer.parseInt(jjTools.getParameter(request, _val18)) : 0);
            map.put(_prop19, jjTools.getParameter(request, _prop19));
            map.put(_val19, jjNumber.isDigit(jjTools.getParameter(request, _val19)) ? Integer.parseInt(jjTools.getParameter(request, _val19)) : 0);
            map.put(_prop20, jjTools.getParameter(request, _prop20));
            map.put(_val20, jjNumber.isDigit(jjTools.getParameter(request, _val20)) ? Integer.parseInt(jjTools.getParameter(request, _val20)) : 0);
            map.put(_price1, jjNumber.isDigit(jjTools.getParameter(request, _price1)) ? Integer.parseInt(jjTools.getParameter(request, _price1)) : 0);
            map.put(_price2, jjNumber.isDigit(jjTools.getParameter(request, _price2)) ? Integer.parseInt(jjTools.getParameter(request, _price2)) : 0);
            //By Md
            map.put(_abstract, jjTools.getParameter(request, _abstract));
            map.put(_content, jjTools.getParameter(request, _content));

            String category = jjTools.getParameter(request, _category_id);
            map.put(_category_id, jjNumber.isDigit(category) ? Integer.parseInt(category) : 1);

            String priority = jjTools.getParameter(request, _priority);
            map.put(_priority, jjNumber.isDigit(priority) ? Integer.parseInt(priority) : 0);

            map.put(_date, jjCalendar_IR.getDatabaseFormat_8length(jjTools.getParameter(request, _date), true));

            map.put(_like, jjTools.getParameter(request, _like));
            map.put(_dislike, jjTools.getParameter(request, _dislike));

            map.put(_visit, jjTools.getParameter(request, _visit));

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "Ø¹Ù…Ù„ÛŒØ§Øª ÙˆÛŒØ±Ø§ÛŒØ´ Ø¨Ù‡ Ø¯Ø±Ø³ØªÛŒ ØµÙˆØ±Øª Ù†Ú¯Ø±Ù�Øª.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjProduct.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "Ø¹Ù…Ù„ÛŒØ§Øª Ø­Ø°Ù� Ø¨Ù‡ Ø¯Ø±Ø³ØªÛŒ ØµÙˆØ±Øª Ù†Ú¯Ø±Ù�Øª";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjProduct.refresh();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "Ø±Ú©ÙˆØ±Ø¯ÛŒ Ø¨Ø§ Ø§ÛŒÙ† Ú©Ø¯ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            StringBuffer html2 = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal(_code, row.get(0).get(_code)));
            html.append(Js.setVal(_name, row.get(0).get(_name)));
            html.append(Js.setVal(_page, row.get(0).get(_page)));
            html.append(Js.setVal(_pic1, row.get(0).get(_pic1)));
            html.append(Js.setVal(_pic2, row.get(0).get(_pic2)));
            html.append(Js.setVal(_pic3, row.get(0).get(_pic3)));
            html.append(Js.setVal(_pic4, row.get(0).get(_pic4)));
            html.append(Js.setVal(_pic5, row.get(0).get(_pic5)));
            html.append(Js.setVal(_pic6, row.get(0).get(_pic6)));
            html.append(Js.setVal(_price1, row.get(0).get(_price1)));
            html.append(Js.setVal(_price2, row.get(0).get(_price2)));
            html.append(Js.setVal(_prop1, row.get(0).get(_prop1)));
            html.append(Js.setVal(_val1, row.get(0).get(_val1)));
            html.append(Js.setVal(_prop2, row.get(0).get(_prop2)));
            html.append(Js.setVal(_val2, row.get(0).get(_val2)));
            html.append(Js.setVal(_prop3, row.get(0).get(_prop3)));
            html.append(Js.setVal(_val3, row.get(0).get(_val3)));
            html.append(Js.setVal(_prop4, row.get(0).get(_prop4)));
            html.append(Js.setVal(_val4, row.get(0).get(_val4)));
            html.append(Js.setVal(_prop5, row.get(0).get(_prop5)));
            html.append(Js.setVal(_val5, row.get(0).get(_val5)));
            html.append(Js.setVal(_prop6, row.get(0).get(_prop6)));
            html.append(Js.setVal(_val6, row.get(0).get(_val6)));
            html.append(Js.setVal(_prop7, row.get(0).get(_prop7)));
            html.append(Js.setVal(_val7, row.get(0).get(_val7)));
            html.append(Js.setVal(_prop8, row.get(0).get(_prop8)));
            html.append(Js.setVal(_val8, row.get(0).get(_val8)));
            html.append(Js.setVal(_prop9, row.get(0).get(_prop9)));
            html.append(Js.setVal(_val9, row.get(0).get(_val9)));
            html.append(Js.setVal(_prop10, row.get(0).get(_prop10)));
            html.append(Js.setVal(_val10, row.get(0).get(_val10)));
            html.append(Js.setVal(_prop11, row.get(0).get(_prop11)));
            html.append(Js.setVal(_val11, row.get(0).get(_val11)));
            html.append(Js.setVal(_prop12, row.get(0).get(_prop12)));
            html.append(Js.setVal(_val12, row.get(0).get(_val12)));
            html.append(Js.setVal(_prop13, row.get(0).get(_prop13)));
            html.append(Js.setVal(_val13, row.get(0).get(_val13)));
            html.append(Js.setVal(_prop14, row.get(0).get(_prop14)));
            html.append(Js.setVal(_val14, row.get(0).get(_val14)));
            html.append(Js.setVal(_prop15, row.get(0).get(_prop15)));
            html.append(Js.setVal(_val15, row.get(0).get(_val15)));
            html.append(Js.setVal(_prop16, row.get(0).get(_prop16)));
            html.append(Js.setVal(_val16, row.get(0).get(_val16)));
            html.append(Js.setVal(_prop17, row.get(0).get(_prop17)));
            html.append(Js.setVal(_val17, row.get(0).get(_val17)));
            html.append(Js.setVal(_prop18, row.get(0).get(_prop18)));
            html.append(Js.setVal(_val18, row.get(0).get(_val18)));
            html.append(Js.setVal(_prop19, row.get(0).get(_prop19)));
            html.append(Js.setVal(_val19, row.get(0).get(_val19)));
            html.append(Js.setVal(_prop20, row.get(0).get(_prop20)));
            html.append(Js.setVal(_val20, row.get(0).get(_val20)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            //By Md                       
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);
            html.append(Js.setVal(_abstract, row.get(0).get(_abstract)));
            html.append(Js.setValEditor(_content, row.get(0).get(_content)));
            /*Ø§Ú¯Ø± Ù…Ø­ØµÙˆÙ„ Ø¹Ú©Ø³ Ø¯Ø§Ø´Øª*/
            if (!row.get(0).get(_pic1).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview1", "src", row.get(0).get(_pic1).toString()));
            }
            if (!row.get(0).get(_pic2).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview2", "src", row.get(0).get(_pic2).toString()));
            }
            if (!row.get(0).get(_pic3).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview3", "src", row.get(0).get(_pic3).toString()));
            }
            if (!row.get(0).get(_pic4).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview4", "src", row.get(0).get(_pic4).toString()));
            }
            if (!row.get(0).get(_pic5).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview5", "src", row.get(0).get(_pic5).toString()));
            }
            if (!row.get(0).get(_pic6).toString().isEmpty()) {
                html.append(Js.setAttr("#account_product_pic_name_preview6", "src", row.get(0).get(_pic6).toString()));
            }

            html.append(Js.setVal(_priority, row.get(0).get(_priority)));
            html.append(Js.setValDate(_date, row.get(0).get(_date)));

            html.append(Js.setVal(_lang, row.get(0).get(_lang)));
            html.append(Js.setVal(_parent, row.get(0).get(_parent)));

            Integer likes = Integer.valueOf(row.get(0).get(_like).toString());
            html.append(Js.setVal(_like, likes));
            if (likes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal(_like + "_checkbox", 0));
                html.append(Js.setAttr(_like, "disabled", "true"));
            } else {
                html.append(Js.setVal(_like + "_checkbox", 1));
                html.append(Js.removeAttr(_like, "disabled"));
            }

            Integer dislikes = Integer.valueOf(row.get(0).get(_dislike).toString());
            html.append(Js.setVal(_dislike, dislikes));
            if (dislikes < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal(_dislike + "_checkbox", 0));
                html.append(Js.setAttr("#" + _dislike, "disabled", "true"));
            } else {
                html.append(Js.setVal(_dislike + "_checkbox", 1));
                html.append(Js.removeAttr(_dislike, "disabled"));
            }

            Integer visit = Integer.valueOf(row.get(0).get(_visit).toString());
            html.append(Js.setVal(_visit, visit));
            if (visit < 0) {//That means it is disabled now and it has been enabled
                html.append(Js.setVal(_visit + "_checkbox", 0));
                html.append(Js.setAttr(_visit, "disabled", "true"));
            } else {
                html.append(Js.setVal(_visit + "_checkbox", 1));
                html.append(Js.removeAttr(_visit, "disabled"));
            }

            //html.append(Js.setValEditor(_content, row.get(0).get(_content)));
            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_Product\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_Product", Js.jjProduct.edit()));
            }
            if (accDel) {
                html2.append("<input type=\"button\" id=\"delete_Product\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                html.append(Js.buttonMouseClick("#delete_Product", Js.jjProduct.delete(id)));
            }

            //By Md
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabaseWeb.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_product' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_product", Js.jjProduct.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_product' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_product", Js.jjProduct.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabaseWeb.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_product' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_product", Js.jjProduct.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_product' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_product", Js.jjProduct.addAr(id)));
                    }
                }
            }
            return (Js.setHtml("#Product_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String searchProduct(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String text = jjTools.getParameter(request, "text");
            String panel = jjTools.getParameter(request, "panel");
            String pr = panel.replace("pr", "");
            if (!text.equals("")) {
                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _name + " LIKE '%" + text + "%' OR " + _code + " LIKE '%" + text + "%' " + (jjNumber.isDigit(text) ? "OR id=" + text : "")));
                if (row.size() > 0) {
                    String find = "";
                    for (int i = 0; i < row.size(); i++) {
                        find += "<a style='color:blue' onclick='setProductToFactor(" + row.get(i).get(_id) + "," + pr + ");'>"
                                + row.get(i).get(_id) + ". " + row.get(i).get(_name) + "</a><br/>";
                    }
                    return Js.setHtml("#" + panel, find) + (row.size() > 0 ? Js.setVal("#account_factor_pr_id_" + pr, row.get(0).get(_id)) : "");
                } else {
                    return Js.setHtml("#" + panel, "Ù…ÙˆØ±Ø¯ÛŒ ÛŒØ§Ù�Øª Ù†Ø´Ø¯.") + Js.setVal("#" + panel, "") + Js.setVal("#account_factor_pr_id_" + pr, "");
                }
            } else {
                return Js.setHtml("#" + panel, "");
            }
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String setProductToFactor(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String pr = panel.replace("pr", "");
            String hasAccess = Access_User.getAccessDialog(request, db, rul_dlt);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "Ù…Ø´ØªØ±ÛŒ Ø¨Ø§ Ø§ÛŒÙ† Ú©Ø¯ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯";
                return Js.dialog(errorMessage);
            }

            StringBuffer html = new StringBuffer();

            html.append(Js.setVal("#account_factor_pr_name_" + pr, row.get(0).get(_name)));
            html.append(Js.setVal("#account_factor_pr_unit_" + pr, row.get(0).get(_val1)));
            html.append(Js.setVal("#account_factor_pr_fee_" + pr, row.get(0).get(_price2)));
            html.append(Js.setVal("#" + _id + "_" + pr, row.get(0).get(_id)));
            html.append("calculateSum" + pr + "();\n");
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    /*
     *return a list of product by catrgory_id /n if catrgory_id==0 then return top product(cat 1,2) *
     */

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0         
        try {
            if (jjTools.isLangEn(request)) {
//                return getList_En(request, db, isPost);
            }
            if (jjTools.isLangAr(request)) {
//                return getList_Ar(request, db, isPost);
            }
            StringBuffer html = new StringBuffer();//for Div,Span and other Html elements
            StringBuffer html3 = new StringBuffer();//for JQuery statements
            String panel = jjTools.getParameter(request, "panel");
            panel = panel == null ? "sw" : panel;
            /*Ø·Ø¨Ù‚Ù‡ Ø¨Ù†Ø¯ÛŒ Ù…Ø­ØµÙˆÙ„Ø§Øª Ø±Ø§ Ø¨Ø±Ù…ÛŒÚ¯Ø±Ø¯Ø§Ù†Ø¯*/
//            html3.append(Category_Product.getHierarchyDiv(re, db));
            Integer category_id = new Integer(jjTools.getParameter(request, "id").toString());
//          category_id = jjNumber.isDigit(jjTools.getParameter(request, "id").toString()) ? id : 1;
            List<Map<String, Object>> topProductRow;
            if (category_id == 0) {
                topProductRow = jjDatabaseWeb.separateRow(
                        db.Select(Product.tableName, Product._lang + "=1 AND " + Product._priority + "<=2"));
            } else {
                topProductRow = jjDatabaseWeb.separateRow(
                        db.Select(Product.tableName, Product._lang + "=1 AND " + Product._category_id + "=" + category_id));
            }

            //---------------one product post creation
            /*Ù„ÛŒØ³Øª Ù…Ø­ØµÙˆÙ„Ø§Øª Ø±Ø§ Ø¨Ø± Ù…ÛŒÚ¯Ø±Ø¯Ø§Ù†Ø¯*/
            StringBuilder temphtml = new StringBuilder();//for Div,Span and other Html elements
            html.append("<div id='swTopproductDiv' class='topproductDiv'></div>");
            if (topProductRow.isEmpty()) {
                temphtml.append("<div class='noAnyThing'>!!! Ø¯Ø± Ø§ÛŒÙ† Ø¨Ø®Ø´ Ù…ÙˆØ±Ø¯ÛŒ Ø¨Ø±Ø§ÛŒ Ù†Ù…Ø§ÛŒØ´ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯<div>");
            } else {
                for (int i = 0; i < topProductRow.size(); i++) {
                    String id = topProductRow.get(i).get(_id).toString();
                    temphtml.append("<div class='productmainDiv'>");
                    temphtml.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(topProductRow.get(i).get(_date).toString()) + "</span>");
                    int visit = new Integer(topProductRow.get(i).get(_visit).toString());
                    if (visit >= 0) {
                        temphtml.append("<div class='productvisitDiv' >" + visit + " Ø¨Ø§Ø± Ù…Ø´Ø§Ù‡Ø¯Ù‡ </div>");
                    }
                    int disLikes = new Integer(topProductRow.get(i).get(_dislike).toString());
                    if (disLikes >= 0) {
                        temphtml.append("<div class='productDisLikeDiv' onclick='productDisLike(" + id + ");' >" + disLikes + " Ù…Ø®Ø§Ù„Ù� </div>");
                    }
                    int likes = new Integer(topProductRow.get(i).get(_like).toString());
                    if (likes >= 0) {
                        temphtml.append("<div class='productlikeDiv' onclick='productLike(" + id + ");' >" + likes + " Ù…ÙˆØ§Ù�Ù‚ </div>");
                    }
                   
                    String src1 = topProductRow.get(i).get(_pic1).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src1.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc1 = src1.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc1 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src1 + "'/>");
                    }
                    String src2 = topProductRow.get(i).get(_pic2).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src2.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc2 = src2.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc2 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src2 + "'/>");
                    }
                    String src3 = topProductRow.get(i).get(_pic1).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src3.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc3 = src3.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc3 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src3 + "'/>");
                    }
                    String src4 = topProductRow.get(i).get(_pic1).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src4.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc4 = src4.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc4 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src4 + "'/>");
                    }
                    String src5 = topProductRow.get(i).get(_pic1).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src5.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc5 = src5.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc5 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src5 + "'/>");
                    }
                    String src6 = topProductRow.get(i).get(_pic1).toString();
                    //Ø§Ú¯Ø± ØªØµÙˆÛŒØ±Ø§Ø² Ù…ÛŒØ§Ù† ØªØµØ§ÙˆÛŒØ± Ø³Ø§ÛŒØª Ø¨ÙˆØ¯ØŒ ØªØµÙˆÛŒØ± Ú©ÙˆÚ†Ú© Ø±Ø§ Ø¨Ø±Ú¯Ø±Ø¯Ø§Ù†Ø¯
                    if (src6.matches("upload/p[0-9]{10}.{4}")) {
                        String smalPicSrc6 = src5.replace(".", "_small.");//select small pic
                        temphtml.append("<img class='productPicDiv' src='" + smalPicSrc6 + "'/>");
                    } else {
                        temphtml.append("<img class='productPicDiv' src='" + src6 + "'/>");
                    }
                    
                    temphtml.append("<span class='productTitlespan'><h3>" + topProductRow.get(i).get(_name).toString() + "</h3></span>");
                    //By Md----------------------
                  /*Ù†Ù…Ø§ÛŒØ´ Ø¬Ø²Ø¦ÛŒØ§Øª Ù…Ø­ØµÙˆÙ„*/
                    temphtml.append("<div class='productabstarctDiv'><h4>" + topProductRow.get(i).get(_page).toString());
                    for (int j = 1; j <= 20; j++) {
                        String Key = "account_product_val" + String.valueOf(j);
                        if ((topProductRow.get(i).get(Key) != null) && (!topProductRow.get(i).get(Key).toString().equals("0"))) {
//                        if (j == 1) {
//                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        } else {
                            temphtml.append("<span class='productTitlespan'><h5>" + topProductRow.get(i).get(Key).toString() + "</h5></span>");
//                        }
                        }
                    }

                    temphtml.append("</h4></div>");
                    //----------------------------
                    temphtml.append("<span class='moreDatale'>"
                            + "<a onclick='getOneproduct(" + id + ");'>" + "Ø§Ø¯Ø§Ù…Ù‡ Ù…Ø·Ù„Ø¨" + "</a>"
                            + "</span>");
                    temphtml.append("<span class='coGruopproduct'>"
                            + "<a onclick='swGetProducts(" + topProductRow.get(i).get(_category_id).toString() + ");'>" + "Ù…Ø·Ø§Ù„Ø¨ Ù…Ø±ØªØ¨Ø·" + "</a>"
                            + "</span>");
                    temphtml.append("</div>");
                }
            }
            html3.append(Js.setHtml("#swTopproductDiv", temphtml.toString()));
            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            return html2 + html3;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String getOneProduct(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuilder html = new StringBuilder();//for Div,Span and other Html elements
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, _id);
            panel = panel == null ? "sw" : panel;
            //to incriment visited time
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _visit + " = " + _visit + "+1 WHERE " + _id + "=" + id);
            List<Map<String, Object>> row;
            row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + " = " + id));
            if (row.isEmpty()) {
                String errorMessage = "Ø±Ú©ÙˆØ±Ø¯ÛŒ Ø¨Ø§ Ø§ÛŒÙ† Ú©Ø¯ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            html.append("<div>");
            html.append("<span class='productDatespan'>" + jjCalendar_IR.getViewFormat(row.get(0).get(_date).toString()) + "</span>");
            int visit = new Integer(row.get(0).get(_visit).toString());
            if (visit >= 0) {
                html.append("<div class='productvisitDiv' >" + visit + " Ø¨Ø§Ø± Ù…Ø´Ø§Ù‡Ø¯Ù‡ </div>");
            }
            int disLikes = new Integer(row.get(0).get(_dislike).toString());
            if (disLikes >= 0) {
                html.append("<div class='productDisLikeDiv' onclick='productDisLike(" + id + ");' >" + disLikes + " Ù…Ø®Ø§Ù„Ù� </div>");
            }
            int likes = new Integer(row.get(0).get(_like).toString());
            if (likes >= 0) {
                html.append("<div class='productlikeDiv' onclick='productLike(" + id + ");' >" + likes + " Ù…ÙˆØ§Ù�Ù‚ </div>");
            }
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic1).toString() + "'/>");
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic2).toString() + "'/>");
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic3).toString() + "'/>");
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic4).toString() + "'/>");
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic5).toString() + "'/>");
            html.append("<img class='productPicDiv' src='" + row.get(0).get(_pic6).toString() + "'/>");
            html.append("<span class='productTitlespan'><h3>" + row.get(0).get(_name).toString() + "<h3></span>");
            html.append("<div class='productabstarctDiv'>" + row.get(0).get(_abstract).toString() + "</div>");
            html.append("<div id='newContentDiv' class='newContentDiv'></div>");

            html.append("<span class='moreDatale'>"
                    + "<a onclick='swGetProducts(" + row.get(0).get(_category_id).toString() + ");'>" + " Ù…Ø·Ø§Ù„Ø¨ Ù…Ø±ØªØ¨Ø·" + "</a>"
                    + "</span>");
            html.append("</div>");

            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
            html2 += Js.setHtml("#newContentDiv", row.get(0).get(_content).toString());
//            row.get(0).get(_content).toString()
//        html2 += Js.table("#sss", height, sort, "");
            return html2;
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    //By Md
    public static String productDisLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _dislike + " = " + _dislike + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String productLike(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            boolean flag = db.otherStatement("UPDATE " + tableName + " SET " + _like + " = " + _like + "+1 WHERE " + _id + "=" + id);
            return String.valueOf(flag);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "Ø±Ú©ÙˆØ±Ø¯ÛŒ Ø¨Ø§ Ø§ÛŒÙ† Ú©Ø¯ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_name);
            if (title != null) {
                html.append(Js.setVal("#" + _name, title));
            }
            Object priority = row.get(0).get(_priority);
            if (priority != null) {
                html.append(Js.setVal("#" + _priority, priority));
            }
            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate("#" + _date, date));
            }
            Object category_id = row.get(0).get(_category_id);
            if (category_id != null) {
                html.append(Js.setVal("#" + _category_id, category_id));
            }
            html.append(Js.setVal("#" + _lang, 2));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Product_button", "<input type=\"button\" id=\"insert_product_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_product_new", Js.jjProduct.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "Ú©Ø¯");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "Ø±Ú©ÙˆØ±Ø¯ÛŒ Ø¨Ø§ Ø§ÛŒÙ† Ú©Ø¯ ÙˆØ¬ÙˆØ¯ Ù†Ø¯Ø§Ø±Ø¯.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            Object title = row.get(0).get(_name);
            if (title != null) {
                html.append(Js.setVal("#" + _name, title));
            }
            Object priority = row.get(0).get(_priority);
            if (priority != null) {
                html.append(Js.setVal("#" + _priority, priority));
            }
            Object date = row.get(0).get(_date);
            if (date != null) {
                html.append(Js.setValDate("#" + _date, date));
            }
            Object category_id = row.get(0).get(_category_id);
            if (category_id != null) {
                html.append(Js.setVal("#" + _category_id, category_id));
            }
            html.append(Js.setVal("#" + _lang, 3));
            Object content = row.get(0).get(_content);
            if (content != null) {
                html.append(Js.setValEditor(_content, content));
            }
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Product_button", "<input type='button' id='insert_product_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_product_new_ar", Js.jjProduct.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
//    public static String getList_En(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            ServerLog.Print("Run: product.getList_En");
//            StringBuffer html = new StringBuffer();
//            StringBuffer html3 = new StringBuffer();
//            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
//            String panel = jjTools.getParameter(request, "panel");
//            panel = panel == null ? "sw" : panel;
//            List<Map<String, Object>> rowCategory = jjDatabaseWeb.separateRow(db.Select(Category_product.tableName, Category_product._parent + "=" + id + " AND " + Category_product._lang + "=2"));
//            html3.append("<span class='productLink'>product</span><span class='productLinkFlash'>&nbsp;>&nbsp;</span>");
//            if (rowCategory.size() > 0) {
//                html3.append("<a class='productLink' onclick='swGetProducts("
//                        + rowCategory.get(0).get(Category_product._parent) + ");'>" + rowCategory.get(0).get(Category_product._title) + "</a>");
//
//                List<Map<String, Object>> categoryRow = jjDatabaseWeb.separateRow(db.Select(Category_product.tableName, Category_product._lang + "=2"));
//                html3.append("<span class='productLinkFlash'>&nbsp;&nbsp;(</span>");
//                int counter2 = 0;
//                for (int i = 0; i < categoryRow.size(); i++) {
//                    if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_product._parent)).getRowCount() > 0) {
//                        if (!rowCategory.get(0).get(Category_product._id).toString().equals(categoryRow.get(i).get(Category_product._id).toString())) {
//                            counter2 += 1;
//                            html3.append((counter2 == 1 ? "<span class='productLinkFlash'> or </span>" : "<span class='productLinkFlash'>, </span>") + "<a  class='productLink' onclick='swGetProducts("
//                                    + categoryRow.get(i).get(Category_product._parent) + ");' >"
//                                    + categoryRow.get(i).get(Category_product._title) + "</a>");
//                        }
//                    }
//                }
//
//                html3.append("<span class='productLinkFlash'>)</span><br/><br/>");
//                if (counter2 > 0) {
//                    html.append(html3.toString());
//                }
//                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _category_id + "=" + rowCategory.get(0).get(Category_product._parent) + " AND " + _lang + "=2"));
//                int counter = 0;
//                for (int i = 0; i < row.size(); i++) {
//                    counter += 1;
//                    html.append("<a dir='ltr' onclick='swproduct(" + row.get(i).get(_id) + ");' class='mousePointer'>");
//                    html.append("<span class='productListDate'>" + counter + ". ");
////                    html.append(jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()));
//                    jjCalendar_IR ir = new jjCalendar_IR(row.get(i).get(_date).toString());
//                    jjCalendar_EN en = ir.convertPersianToGregorian();
//                    html.append(en.getYear_4length() + "/" + en.getMonth_2length() + "/" + en.getDay_2length());
//                    html.append(" </span><span class='productList'>");
//                    html.append(row.get(i).get(_title).toString() + "</span></a><br/>");
//                }
//
//
//            }
//            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
////        html2 += Js.table("#sss", height, sort, "");
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }
//
//    public static String getList_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            ServerLog.Print("Run: product.getList_Ar");
//            StringBuffer html = new StringBuffer();
//            StringBuffer html3 = new StringBuffer();
//            String id = jjTools.getParameter(request, "id");
//            id = jjNumber.isDigit(id) ? id : "1";
//            String panel = jjTools.getParameter(request, "panel");
//            panel = panel == null ? "sw" : panel;
//            List<Map<String, Object>> rowCategory = jjDatabaseWeb.separateRow(db.Select(Category_product.tableName,
//                    Category_product._parent + "=" + id + " AND " + Category_product._lang + "=3"));
//
//            html3.append("<span class='productLink'>");
//            html3.append("Ø£Ø®Ø¨Ø§Ø±");
//            html3.append("</span><span class='productLinkFlash'>&nbsp;>&nbsp;</span>");
//            if (rowCategory.size() > 0) {
//                html3.append("<a class='productLink' onclick='swGetProducts("
//                        + rowCategory.get(0).get(Category_product._parent) + ");'>" + rowCategory.get(0).get(Category_product._title) + "</a>");
//
//                List<Map<String, Object>> categoryRow = jjDatabaseWeb.separateRow(db.Select(Category_product.tableName, Category_product._lang + "=3"));
//                html3.append("<span class='productLinkFlash'>&nbsp;&nbsp;(</span>");
//                int counter2 = 0;
//                for (int i = 0; i < categoryRow.size(); i++) {
//                    if (db.Select(tableName, _category_id + "=" + categoryRow.get(i).get(Category_product._parent)).getRowCount() > 0) {
//                        if (!rowCategory.get(0).get(Category_product._id).toString().equals(categoryRow.get(i).get(Category_product._id).toString())) {
//                            counter2 += 1;
//                            html3.append((counter2 == 1 ? "<span class='productLinkFlash'> Ø£Ùˆ </span>" : "<span class='productLinkFlash'>, </span>")
//                                    + "<a  class='productLink' onclick='swGetProducts("
//                                    + categoryRow.get(i).get(Category_product._parent) + ");' >"
//                                    + categoryRow.get(i).get(Category_product._title) + "</a>");
//                        }
//                    }
//                }
//
//                html3.append("<span class='productLinkFlash'>)</span><br/><br/>");
//                if (counter2 > 0) {
//                    html.append(html3.toString());
//                }
//                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _category_id + "=" + rowCategory.get(0).get(Category_product._parent) + " AND " + _lang + "=3"));
//                int counter = 0;
//                for (int i = 0; i < row.size(); i++) {
//                    counter += 1;
//                    html.append("<a dir='rtl' onclick='swproduct(" + row.get(i).get(_id) + ");' class='mousePointer'>");
//                    html.append("<span class='productListDate'>" + counter + ". ");
//                    html.append(jjCalendar_IR.getViewFormat(row.get(i).get(_date).toString()) + " </span><span class='productList'>");
//                    html.append("</span></a><br/>");
//                }
//
//
//            }
//            String html2 = "$('#" + panel + "').html(\"" + html.toString() + "\");\n";
////        html2 += Js.table("#sss", height, sort, "");
//            return html2;
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }  
}
