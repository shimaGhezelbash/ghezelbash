package cms.tools;

import cms.access.*;
import cms.cms.*;
import java.io.*;
import static java.lang.System.out;
import java.lang.reflect.*;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import jj.jjCalendar_IR;
import jj.jjDatabaseWeb;


public class Server extends HttpServlet {

    public static String defaultLang = "fa";// en
    public static String userNameSMS = "0";
    public static String passwordSMS = "0";
    
    //-------------------------------------------------------------------------------
    public static jjDatabaseWeb db;
    public static HttpServletResponse Publicresponse;

    
    private static List<Class> clazzes = new ArrayList<Class>();

    public static List<Class> getClazzes() {
        if (clazzes.isEmpty()) {
            clazzes.add(Category_Product.class);
            clazzes.add(Comment.class);
            clazzes.add(Access_Group.class);
            clazzes.add(Access_Group_User.class);
            clazzes.add(Access_User.class);
            clazzes.add(Product.class);
            clazzes.add(jjDatabaseWeb.class);
        }
        return clazzes;
    }

    protected void run(HttpServletRequest request, HttpServletResponse response, boolean isPost) throws ServletException, IOException {
        Publicresponse = response;
        StringBuilder script = new StringBuilder();
        try (PrintWriter out = jjTools.getWriterUTF8(request, response)) {
            Connect();
//            jjTools.setLang(request);
//            request.getSession(true).setMaxInactiveInterval(6000);
            script.append(jjTools.setLang(request));
            String Action = jjTools.getParameter(request, "do");
            String clazz = jjTools.getParameter(request, "tbl");
            String method = jjTools.getParameter(request, "act");
//            String dbName = jjTools.getParameter(request, "db");
//            if (!dbName.equals("")) {
//                databaseName = dbName;
//                jjTools.setSessionAttribute(request, "databaseName", dbName);
//            }
//            databaseName = jjTools.getSessionAttribute(request, "databaseName").equals("") ? databaseName : jjTools.getSessionAttribute(request, "databaseName");
            int dot = Action.indexOf(".");
            if (dot > -1) {
                clazz = Action.substring(0, dot);
                method = Action.substring(dot + 1, Action.length());
            }
            // -----------------------------------------------------------------
            script.append(run(getClazzes(), clazz, method, request, db, isPost));
            ServerLog.Print(script);//By Md
            //ServerLog.Print(script.toString());
            out.print(script);
            // Runtime.getRuntime().gc();
//            System.gc();
        } catch (Exception ex) {
            out.print(ErrorHandler(ex));
        } finally {
            System.gc();
//            db.disConnectCustom();
        }
    }

    public static void Connect() throws SQLException, ClassNotFoundException {
        db=jjDatabaseWeb.getInstance();
    }

    public static String run(List<Class> clazz, String className, String methodName, HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws Exception {
        try {
            jjTools.ShowAllParameter(request);
//            jjTools.ShowAllAttribute(request);
            for (int j = 0; j < clazz.size(); j++) {
                if (clazz.get(j).getSimpleName().equals(className)) {
                    Method[] methods = clazz.get(j).getMethods();
                    for (int i = 0; i < methods.length; i++) {
                        if (methods[i].getName().equals(methodName)) {
                            ServerLog.Print("Run: " + className + "." + methods[i].getName() + "()");
                            return methods[i].invoke(null, request, db, isPost).toString();
                        }
                    }
                }
            }
            return "";
        } catch (Exception ex) {
            return ErrorHandler(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run(request, response, true);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        run(request, response, false);
    }

    public static String noAjaxRun(String parameters, HttpServletRequest request) {
        try {
            ServerLog.Print("------- noAjaxRun ---------");
            String action = parameters;
            String reqClazz = "";
            String method = "";
            int index1 = action.indexOf("do=");
            int index2 = action.indexOf(".");
            if (index1 >= 0 && index2 > 0) {// do  وجود دارد؟
                index1 = action.indexOf("=", index1) + 1;
                reqClazz = action.substring(index1, index2);
                index1 = index2 + 1;// بعد از  نقطه برای پیدا کردن تابع
                index2 = action.indexOf("&");// بعد از  نقطه برای پیدا کردن تابع
                method = action.substring(index1, index2);
            }
            String attributes[] = parameters.split("&");
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].matches(".*=.*")) {
                    ServerLog.Print(attributes[i]);
                    String attribNameAndValue[] = attributes[i].split("=");
                    request.setAttribute(attribNameAndValue[0], attribNameAndValue[1]);
                }
            }
            jjTools.ShowAllAttribute(request);
//            String reqClazz = jjTools.getParameter(request, "tbl");
//            String method = jjTools.getParameter(request, "act");
//            String method = jjTools.getParameter(request, "act");

//            String reqClazz = jjTools.getParameter(request, "tbl");
            int dot = action.indexOf(".");
            String content = cms.tools.Server.run(Server.getClazzes(), reqClazz, method, request, db, false);
            return content;
        } catch (Exception ex) {
            System.out.println(ex);
            ServerLog.Print(ex);
            return ex.toString();
        }
    }

    public static String ErrorHandler(Exception ex) {
        try {
            db= jjDatabaseWeb.getInstance();
            System.err.println(ex.toString());
            StringBuilder dbErrorWrite = new StringBuilder();
//            StringBuffer returnDialog = new StringBuffer();
            StackTraceElement[] stackTrace = ex.getStackTrace();
            for (int i = 0; i < stackTrace.length; i++) {
                StackTraceElement st = stackTrace[i];
                if (st.getClassName().startsWith("cms") || st.getClassName().startsWith("tice")) {
                    if (!st.getClassName().startsWith("cms.tools.Server")) {
                        dbErrorWrite.append("<p style='direction:ltr'>").append(st.getClassName()).append(".").append(st.getMethodName()).append(" > line:").append(st.getLineNumber()).append("</p>");
                    }
                }
            }
            dbErrorWrite.append("<p style='direction:ltr'>").append(ex.toString()).append("</p>");
//            returnDialog.append("<p style='float: left;direction:ltr'>" + ex.toString() + "</p>");

            Map<String, Object> map = new HashMap<String, Object>();
            map.put(Comment._date, new jjCalendar_IR().getDBFormat_8length());
            map.put(Comment._email, "mrsalesi@gmail.com");
            map.put(Comment._name_Full, "سیستم");
            map.put(Comment._tell, "03112683807");
            map.put(Comment._text, dbErrorWrite.toString());
            map.put(Comment._title, "مشکلی در سیستم");
            map.put(Comment._answer, "");
            db.insert(Comment.tableName, map);
            return Js.dialog(dbErrorWrite.toString());
        } catch (Exception ex2) {
            ServerLog.Print(ex2);
            return Js.dialog("Error in Server ErrorHandler");
        }
    }
}
