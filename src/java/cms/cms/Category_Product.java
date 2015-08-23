package cms.cms;

import cms.access.*;
import cms.tools.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import jj.*;

public class Category_Product {

    public static String tableName = "category_product";
    public static String _id = "id";
    public static String _title = "category_product_title";
    public static String _parent = "category_product_parent";
    public static String _lang = "category_product_lang";
    public static String _upperNode = "category_product_upperNode";
    public static String lbl_insert = "ذخیره";
    public static String lbl_delete = "حذف";
    public static String lbl_edit = "ویرایش";
    public static String lbl_add_en = "افزودن زبان انگلیسی";
    public static String lbl_edit_en = "ویرایش بخش انگلیسی";
    public static String lbl_add_ar = "افزودن زبان عربی";
    public static String lbl_edit_ar = "ویرایش بخش عربی";
    public static int rul_rfs = 19;
    public static int rul_ins = 20;
    public static int rul_edt = 21;
    public static int rul_dlt = 22;
    public static int rul_lng = 23;

    

    public static String getList(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            return "list";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * This method return onli " li " tags  menu of top level products use to set sub menu
     * for products
     *
     * @since v1.5.0
     * @return some JQuery statements like :<br/>$('#div1').setHtml('xxxx');
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            
            return "menu";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * This method return hierarchical div for mane page in '#sw' and have some
     * little different whit getHierarchyList() in onclick();
     *
     * @since v1.5.0
     * @return some JQuery statements like
     * :<br/>$('#div1').setHtml('xxxx');<br/>$('#div2').setHtml('xxxx');<br/>,...
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getHierarchyDiv(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {//new in v1.5.0 
        try {
            
            return "hierArchi list";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    /**
     * This method return hierarchical list for cms in where site admin can
     * change parent of node little different whit getHierarchyList() in
     * onclick();
     *
     * @since v1.5.0
     * @return some JQuery statements like
     * :<br/>$('#div1').setHtml('xxxx');<br/>$('#div2').setHtml('xxxx');<br/>,...
     * @param panel whitout # only panel name;('#' is need for selector not
     * panel in entire of this webApp );
     * @param db database connection;
     */
    public static String getHierarchyList(String panel, jjDatabaseWeb db) throws Exception {
        try {
            
            return "get hierarchy list";
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
    /*
     * only get upper nodes and remove children because it is not possible
     * to be one node childern of his child
     * 
     @only use when there is a div  with 'id=hierarchyListDiv',
     */

    
    public static String add_new(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            StringBuffer html = new StringBuffer();
            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Categoryproduct_button", "<input type=\"button\" id=\"insert_Categoryproduct_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Categoryproduct_new", Js.jjCategoryProduct.insert()));
            }
            return html.toString() + getHierarchyList("hierarchyListDiv", db);
//            return html.toString();
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

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_upperNode, jjTools.getParameter(request, _upperNode));

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            if (db.insert(tableName, map).getRowCount() == 0) {
                String errorMessage = "عملیات درج به درستی صورت نگرفت.";
                if (jjTools.getParameter(request, "myLang").equals("en")) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryProduct.refresh() + getOptions(request, db, isPost);
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

            String parent = jjTools.getParameter(request, _parent);
            map.put(_parent, jjNumber.isDigit(parent) ? Integer.parseInt(parent) : 0);

            map.put(_title, jjTools.getParameter(request, _title));
            map.put(_upperNode, jjTools.getParameter(request, _upperNode));

            String lang = jjTools.getParameter(request, _lang);
            map.put(_lang, jjNumber.isDigit(lang) ? Integer.parseInt(lang) : 1);

            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (!db.update(tableName, map, _id + "=" + jjTools.getParameter(request, _id))) {
                String errorMessage = "عملیات ویرایش به درستی صورت نگرفت.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Edit Fail;";
                }
                return Js.dialog(errorMessage);
            }
            return Js.jjCategoryProduct.refresh() + getOptions(request, db, isPost);
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
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            if (id.equals("1")) {
                String errorMessageId2 = "شما اجازه حذف این رکورد را ندارید.";
                if (jjTools.isLangEn(request)) {
                    errorMessageId2 = "You can't delete this record.";
                }
                return Js.dialog(errorMessageId2);
            }
            if (!db.delete(tableName, _id + "=" + id)) {
                String errorMessage = "عملیات حذف به درستی صورت نگرفت";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Delete Fail;";
                }
                return Js.dialog(errorMessage);
            }
            db.delete(tableName, _parent + "=" + id);
            // change product category to default
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Product._category_id, 1);
            db.update(Product.tableName, map, Product._category_id + "=" + id);
            //بعد از پاک کردن یک ریشه، فرزندانش به ریشه 0 انتقال می یابند
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put(Category_Product._upperNode, 0);
            db.update(Category_Product.tableName, map2, Category_Product._upperNode + "=" + id);
            return Js.jjCategoryProduct.refresh() + getOptions(request, db, isPost);
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String select(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();
            StringBuffer html2 = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _lang, row.get(0).get(_lang)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_parent)));
            html.append(Js.setVal("#" + _upperNode, row.get(0).get(_upperNode)));

            boolean accDel = Access_User.hasAccess2(request, db, rul_dlt);
            boolean accEdt = Access_User.hasAccess2(request, db, rul_edt);
            boolean acclng = Access_User.hasAccess2(request, db, rul_lng);

            if (accEdt) {
                html2.append("<input type=\"button\" id=\"edit_Categoryproduct\" value=\"" + lbl_edit + "\" class=\"tahoma10\">");
                html.append(Js.buttonMouseClick("#edit_Categoryproduct", Js.jjCategoryProduct.edit()));
            }
            if (accDel) {
                if (!id.equals("1")) {
                    if (!row.get(0).get(_parent).toString().equals("1")) {
                        html2.append("<input type=\"button\" id=\"delete_Categoryproduct\" value=\"" + lbl_delete + "\" class=\"tahoma10\"  />");
                        html.append(Js.buttonMouseClick("#delete_Categoryproduct", Js.jjCategoryProduct.delete(id)));
                    }
                }
            }
            if (acclng) {
                List<Map<String, Object>> rowEn = jjDatabaseWeb.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=2"));
                if (rowEn.size() > 0) {
                    html2.append("<input type='button' id='edit_en_Categoryproduct' value='" + lbl_edit_en + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_en_Categoryproduct", Js.jjCategoryProduct.select(rowEn.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_en_Categoryproduct' value='" + lbl_add_en + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_en_Categoryproduct", Js.jjCategoryProduct.addEN(id)));
                    }
                }
                List<Map<String, Object>> rowAr = jjDatabaseWeb.separateRow(db.Select(tableName, _parent + "=" + id + " AND " + _lang + "=3"));
                if (rowAr.size() > 0) {
                    html2.append("<input type='button' id='edit_ar_Categoryproduct' value='" + lbl_edit_ar + "' class='tahoma10'  />");
                    html.append(Js.buttonMouseClick("#edit_ar_Categoryproduct", Js.jjCategoryProduct.select(rowAr.get(0).get(_id).toString())));
                } else {
                    if (row.get(0).get(_parent).equals("0")) {
                        html2.append("<input type='button' id='add_ar_Categoryproduct' value='" + lbl_add_ar + "' class='tahoma10' />");
                        html.append(Js.buttonMouseClick("#add_ar_Categoryproduct", Js.jjCategoryProduct.addAr(id)));// Important (addAr)
                    }
                }
            }
            return (Js.setHtml("#Categoryproduct_button", html2.toString())) + html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_EN(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _lang, 2));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Categoryproduct_button", "<input type=\"button\" id=\"insert_Categoryproduct_new\" value=\"" + lbl_insert + "\" class=\"tahoma10\">"));
                html.append(Js.buttonMouseClick("#insert_Categoryproduct_new", Js.jjCategoryProduct.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

    public static String add_Ar(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String id = jjTools.getParameter(request, _id);
            String errorMessageId = jjValidation.isDigitMessageFa(id, "کد");
            if (!errorMessageId.equals("")) {
                if (jjTools.isLangEn(request)) {
                    errorMessageId = jjValidation.isDigitMessageEn(id, "ID");
                }
                return Js.dialog(errorMessageId);
            }
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _id + "=" + id));
            if (row.size() == 0) {
                String errorMessage = "رکوردی با این کد وجود ندارد.";
                if (jjTools.isLangEn(request)) {
                    errorMessage = "Select Fail;";
                }
                return Js.dialog(errorMessage);
            }
            StringBuffer html = new StringBuffer();

            html.append(Js.setVal("#" + tableName + "_" + _id, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _title, row.get(0).get(_title)));
            html.append(Js.setVal("#" + _parent, row.get(0).get(_id)));
            html.append(Js.setVal("#" + _lang, 3));

            boolean accIns = Access_User.hasAccess2(request, db, rul_ins);
            if (accIns) {
                html.append(Js.setHtml("#Categoryproduct_button", "<input type='button' id='insert_Categoryproduct_new_ar' value='" + lbl_insert + "' class='tahoma10'>"));
                html.append(Js.buttonMouseClick("#insert_Categoryproduct_new_ar", Js.jjCategoryProduct.insert()));
            }
            return html.toString();
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }

//    public static String getMenu(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
//        try {
//            StringBuffer html = new StringBuffer();
//            List<Map<String, Object>> row = jjDatabase.separateRow(db.SelectAll(tableName));
//            html.append("&nbsp;&nbsp;<ul>");
//            for (int i = 0; i < row.size(); i++) {
//                html.append("<li onclick='swproductCategory(" + row.get(i).get(_id) + ");'>&nbsp;" + row.get(i).get(_title) + " </li>");
//            }
//            html.append("</ul>");
//            return html.toString();
//        } catch (Exception ex) {
//            return Server.ErrorHandler(ex);
//        }
//    }

    public static String getOptions(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            String panel = jjTools.getParameter(request, "panel");
            String id = jjTools.getParameter(request, "id");
            if (panel.equals("")) {
                panel = "product_category_id_select";
            }
            String selectedCategory = "";
            if (jjNumber.isDigit(id)) {
                List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(Product.tableName, Product._id + "=" + id));
                if (row.size() > 0) {
                    selectedCategory = row.get(0).get(Product._category_id).toString();
                }
            }
            StringBuffer html = new StringBuffer();
            List<Map<String, Object>> row = jjDatabaseWeb.separateRow(db.Select(tableName, _parent + "=0"));
            for (int i = 0; i < row.size(); i++) {
                String toString = row.get(i).get(_id).toString();
                html.append("<option value='" + toString + "' " + (selectedCategory.equals(toString) ? "selected='selected'" : "")
                        + ">" + row.get(i).get(_title).toString() + "</option>");
            }
            return Js.setHtml("#" + panel, html.toString());
        } catch (Exception ex) {
            return Server.ErrorHandler(ex);
        }
    }
}
