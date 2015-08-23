/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usm;

import cms.cms.Setting;
import cms.tools.ServerLog;
import cms.tools.jjTools;
import java.io.File;
import static java.lang.System.out;
import javax.servlet.http.HttpServletRequest;
import jj.jjDatabaseWeb;
import jj.jjFileTxt;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//این دو کلاس را اضافه کردیم زمانی که دستور ئپ را نیو کردیم
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.DefaultTableModel;
import jj.JmHtmlReplasor;
import jj.jjDatabaseQuery;
import static jj.jjDatabaseWeb.getCounterFromFile;
import jj.jjError;

/**
 *
 * @author shima
 */
public class StdInfo {

    private static jj.Enum.DatabaseType dbType = jj.Enum.DatabaseType.Access;
//     private static String url;
    private static String databasePath;
    public static final String tableName = "std_info";
    public static final String _id = "id";
    public static final String _userName = "std_info_userName";
    public static final String _password = "std_info_pass";
    public static final String _name = "std_info_name";
    public static final String _step = "std_info_step";

    //===========================validate========>>
    public static StringBuilder validate(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        String username = request.getParameter(StdInfo._userName);
        String pass = request.getParameter(StdInfo._password);
        StringBuilder result = new StringBuilder("");
        if ((username.isEmpty() || pass.isEmpty())) {
            return result.append("لطفا تمامی اطلاعات را وارد نمایید");
        }
        DefaultTableModel dtm = db.Select(tableName, _userName + " = '" + username + "' AND " + _password + " = '" + pass + "'");
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
        System.out.println("=================================================");
        System.out.println(row.toString());
        System.out.println("=================================================");

        if (row.size() > 0) {
            String id = row.get(0).get(_id).toString();
            ServerLog.Print("login Ok for " + row.get(0).get(_userName) + " " + pass);
            if (Setting.getSesionStatus()) {//اگر ادمین سیستم حالت ذخیره در سشن را فعال کرده بود
                //حالت سشن برای اینکه مصرف حافطه کمتر شودمی تواند توسط ادمین غیر فعال گردد.
                //این کار امنیت را کاهش می دهد
                jjTools.setSessionAttribute(request, "#" + _id, id);
                jjTools.setSessionAttribute(request, "#" + _userName, row.get(0).get(_userName));
                jjTools.setSessionAttribute(request, "#" + _password, row.get(0).get(_password));
            }

            Integer step = Integer.valueOf(row.get(0).get(_step).toString());
            if (step == -1) {//استپ -1 به منزله اینست که کاربر هنوز فرم  اطلاعات خود راتکمیل یا تااید نکرده است
                String address = request.getServletContext().getRealPath("/");
                ServerLog.Print(address);
                File file = new File(address + "/" + "std_form1.html");
                if (!file.exists()) {
                    return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                }
                StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", "").trim());

                //     User_info.selectAllUsers2(request,response,db);
                // DefaultTableModel dtm = db.Select(std_form1, _name + " = '" + username + "' AND " + _password + " = '" + pass + "'");
                //   List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
                JmHtmlReplasor.setAttr(htmlForm, _id, "value", row.get(0).get(id).toString());//قرار دادن آی دی دانشجو در فرم اطلاعاتدانشجویی خوانده شده برای استفاده در مواقعی که سشن فعال نیست
                JmHtmlReplasor.setAttr(htmlForm, _name, "value", row.get(0).get(_name).toString());//قرار دادن مقدار از دیتا بیس در اچ تی ام ال خوانده شده
//            jjDatabaseWeb db;
//            Server.Connect();
//            db = Server.db;
                return htmlForm;//اچ تی ام ال را که خواندیم و مقدار دهی کردیم حالا چاپ می کنیم
            }
            //====================================================>>
            if (step == 2) {
                String address = request.getServletContext().getRealPath("/");
                ServerLog.Print(address);
                File file = new File(address + "/" + "std_form2.html");
                if (!file.exists()) {
                    return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                }
                StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
            }
//===============================================================>>
            if (step == 3) {
                String address = request.getServletContext().getRealPath("/");
                ServerLog.Print(address);
                File file = new File(address + "/" + "std_form3.html");
                if (!file.exists()) {
                    return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                }
                StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
            }
//===============================================================>>
            if (step == 4) {
                String sql = "SELECT COUNT(*) FROM std_info_form4_1";
                dtm = db.otherSelect(sql);
                List<Map<String, Object>> row4_x = jjDatabaseWeb.separateRow(dtm);

                System.out.println(row4_x.toString());
                Integer row4_1_count = Integer.valueOf(row4_x.get(0).get(0).toString());
                System.out.println("_______________________________________________________");
                System.out.println(row4_1_count);
                System.out.println("_______________________________________________________");

                if (row4_1_count <= 255) {
                    String address = request.getServletContext().getRealPath("/");
                    ServerLog.Print(address);
                    File file = new File(address + "/" + "std_form4-1.html");
                    if (!file.exists()) {
                        return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                    }
                    StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                    return htmlForm;
                } else {
                    sql = jjDatabaseQuery.count(StdForm4_2.tableName);// "SELECT COUNT(*) FROM std_info_form4_2";
                    //تعداد را میریزد در اس کیو ال
                    dtm = db.otherSelect(sql);
                    row4_x = jjDatabaseWeb.separateRow(dtm);
                    System.out.println(row4_x.toString());
                    Integer row4_2_count = Integer.valueOf(row4_x.get(0).get(0).toString());
                    //تبدیل تعداد به اینتجر
                    System.out.println("_______________________________________________________");
                    System.out.println(row4_2_count);
                    System.out.println("_______________________________________________________");
                    if (row4_2_count <= 255) {
                        String address = request.getServletContext().getRealPath("/");
                        ServerLog.Print(address);
                        File file = new File(address + "/" + "std_form4-2.html");
                        if (!file.exists()) {
                            return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                        }
                        StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                        return htmlForm;
                    } else {
                        sql = "SELECT COUNT(*) FROM std_info_form4_3";
                        //تعداد را میریزد در اس کیو ال
                        dtm = db.otherSelect(sql);
                        row4_x = jjDatabaseWeb.separateRow(dtm);
                        System.out.println(row4_x.toString());
                        Integer row4_3_count = Integer.valueOf(row4_x.get(0).get(0).toString());
                        //تبدیل تعداد به اینتجر
                        System.out.println("_______________________________________________________");
                        System.out.println(row4_3_count);
                        System.out.println("_______________________________________________________");

                        if (row4_3_count <= 255) {
                            String address = request.getServletContext().getRealPath("/");
                            ServerLog.Print(address);
                            File file = new File(address + "/" + "std_form4-3.html");
                            if (!file.exists()) {
                                return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                            }
                            StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                            return htmlForm;
                        } else {
                            //     String sql = "SELECT COUNT(*) FROM std_info_form4_4";
                            //تعداد را میریزد در اس کیو ال
                            //    dtm = db.otherSelect(sql);
                            //    row4_x = jjDatabaseWeb.separateRow(dtm);
                            //     System.out.println(row4_x.toString());
                            //   Integer row4_3_count = Integer.valueOf(row4_x.get(0).get(0).toString());
                            //تبدیل تعداد به اینتجر
                            //      System.out.println("_______________________________________________________");
                            //   System.out.println(row4_4_count);
                            //     System.out.println("_______________________________________________________");
                            String address = request.getServletContext().getRealPath("/");
                            ServerLog.Print(address);
                            File file = new File(address + "/" + "std_form4-4.html");
                            if (!file.exists()) {
                                return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
                            }
                            StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
                            return htmlForm;
                        }
                    }
                }
            }
        } else {
            return result.append("شما اجازه ذسترسی ندارید");
        }
        return result;
    }

    public static StringBuilder update(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {

        StringBuilder result = new StringBuilder("");
        String name = request.getParameter(StdInfo._name);
        String id = request.getParameter(_id);
        if (Setting.getSesionStatus()) {
            id = request.getSession().getAttribute("#" + _id).toString();
        }
        Map<String, Object> map = new HashMap<>();
        map.put(_name, name);
        String sql = jjDatabaseQuery.update(tableName, map, _id + "=" + id);
        db.executeThisQuery(sql);
//         DefaultTableModel dtm = db.Select(tableName, _id + " = "+ request.getSession().getAttribute("#" + _id).toString());
//          List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
//         
//         if (i > 0) {
//             StringBuilder result = new StringBuilder("ویرایش اطلاعات به درستی انجام شد") ;
//       return result;
//         }
        String address = request.getServletContext().getRealPath("/");
        ServerLog.Print(address);
        File file = new File(address + "/" + "std_form1.html");

        if (!file.exists()) {
            return result.append("اشکال در سرور:فایل مورد نظر یافت نشد");
        }
        StringBuilder htmlForm = new StringBuilder(jjFileTxt.read(file).replace("\n", ""));
//                         User_info.selectAllUsers2(request,response,db);
        DefaultTableModel dtm = db.Select(tableName, _id + " = " + id);
        List<Map<String, Object>> row = jjDatabaseWeb.separateRow(dtm);
        JmHtmlReplasor.setAttr(htmlForm, _name, "value", row.get(0).get(_name).toString());//قرار دادن مقدار از دیتا بیس در اچ تی لم ال
//            jjDatabaseWeb db;
//            Server.Connect();
//            db = Server.db;
        //       String sql2 = "update" + tableName + "set" + _step + " = " + _step + " + " + 2 + " where " + _id + " = " + id;
        String sql2 = "update" + tableName + "set" + _step + " = 2 where " + _id + " = " + id;
        db.executeThisQuery(sql2);
        return htmlForm;//اچ تی ام ال
    }
}
