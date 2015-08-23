/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

import cms.tools.ServerLog;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jj.Enum.DatabaseType;

/**
 *
 * @author Milad
 */
public class jjDatabaseWeb {

    private static DatabaseType dbType = DatabaseType.Access;
    private static String databasePath;
    private ResultSet resultSet;
    private static String serverHost = "127.0.0.1"; //localhost";
    private static String port = "3306";
    private static String databaseName = "db_my_test";
    static String url2;

    private Connection connection;
    private static jjDatabaseWeb accessor;

    private jjDatabaseWeb() throws ClassNotFoundException, SQLException {
//        Class.forName(JdbcOdbcDriver.class.getName());
        // connection = DriverManager.getConnection("Jdbc:Odbc:flashDatabase");
        //--- or next line :
//            String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)}; DBQ=d:\\nemoone1.accdb";
//            connection = DriverManager.getConnection(myDB, "", "");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_my_test?characterSetResults=UTF-8&characterEncoding=UTF-8&useUnicode=yes", "root", "m123456");
        //or
        //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlDatabase?useUnicode=true&amp;characterEncoding=utf-8", "root", "");
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        System.out.println("database driver name is ok");
    }

    public static synchronized jjDatabaseWeb getInstance() throws SQLException, ClassNotFoundException {
        if (accessor == null) {
            accessor = new jjDatabaseWeb();
        }
        return accessor;
    }
//==============================================================================

    public DefaultTableModel getlastInsertID(String tableName) {
        DefaultTableModel otherSelect;
        try {
            otherSelect = otherSelect("SELECT LAST_INSERT_ID()");
//                    if (otherSelect == null) {
//                        return Select(tableName, "id=" + 1);
//                    }
//                    if (otherSelect.getRowCount() > 0) {
//                        return Select(tableName, "id=" + otherSelect.getValueAt(0, 0).toString());
//                    } else {
//                        return Select(tableName, "id=" + 1);
//                    }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return otherSelect;
    }

    public DefaultTableModel insert(String tableName, Map<String, Object> columnNameAndValues) {
        try {
            if (dbType == DatabaseType.File) {
                int counterFromFile = getCounterFromFile(databasePath + "\\" + tableName + "\\Counter ID.dll");
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + counterFromFile + ".txt");
                jjFileTxt.writeKeyValue(rowFile, columnNameAndValues);
            } else {
                String SQL = jjDatabaseQuery.insert(tableName, columnNameAndValues);
                if (otherStatement(SQL)) {
                    DefaultTableModel lastInsertID = getlastInsertID(tableName);
                    if (lastInsertID.getRowCount() > 0) {
                        return Select(tableName, "id=" + lastInsertID.getValueAt(0, 0).toString());
                    } else {
                        return Select(tableName, "id=" + 1);
                    }
                };
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return null;
        }
        return null;
    }

//==============================================================================
    public boolean update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
        try {
            if (dbType == DatabaseType.File) {
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + WHERE + ".txt");
                if (!rowFile.exists()) {
                    return false;
                }
                Map<String, Object> readKeyValue = jjFileTxt.readKeyValue(rowFile);
                readKeyValue.putAll(readKeyValue);
                jjFileTxt.writeKeyValue(rowFile, readKeyValue);
            } else {
                String SQL = jjDatabaseQuery.update(tableName, columnNameAndValues, WHERE);
                return otherStatement(SQL);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return true;
    }
//==============================================================================

    public boolean delete(String tableName, String WHERE) {
        try {
            if (dbType == DatabaseType.File) {
                File rowFile = new File(databasePath + "\\" + tableName + "\\row" + "-" + WHERE + ".txt");
                if (!rowFile.exists()) {
                    return false;
                }
                jjFileTxt.write(rowFile, "");
                jjFile.delete(rowFile);
            } else {
                String SQL = jjDatabaseQuery.delete(tableName, WHERE);
                return otherStatement(SQL);
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return true;
    }
//==============================================================================

    public int count(String tableName) {
        String SQL = jjDatabaseQuery.count(tableName);
        DefaultTableModel dtm = otherSelect(SQL);
        if (dtm.getRowCount() > 0) {
            return Integer.parseInt(dtm.getValueAt(0, 0).toString());
        }
        return 0;
    }

    public DefaultTableModel SelectAll(String tableName) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, "", "");
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel SelectAll(String tableName, String whichField) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, whichField, "");
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel SelectAll(String tableName, String whichField, int everyPage, int pageNo) {
        String SQL = jjDatabaseQuery.select(tableName, whichField, "");
        return otherSelect(SQL, everyPage, pageNo);
    }

    public DefaultTableModel Select(String tableName) {
        return SelectAll(tableName);
    }

    public DefaultTableModel Select(String tableName, String WHERE) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, "", WHERE);
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel Select(String tableName, String whichField, String WHERE) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, whichField, WHERE);
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel Select(String tableName, String whichField, String WHERE, String OrderBy) {
        if (dbType == DatabaseType.File) {
            return SelectFromFile(tableName);
        } else {
            String SQL = jjDatabaseQuery.select(tableName, whichField, WHERE + " ORDER BY " + OrderBy + ";");
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel SelectDistinct(String tableName, String columns) {//add by Mohammad
        if (dbType == DatabaseType.File) {
            return otherSelect("SELECT DISTINCT " + columns + " FROM " + tableName + ";");
        } else {
            String SQL = jjDatabaseQuery.selectDistinct(tableName, columns);
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel SelectDistinct(String tableName, String columns, String where) {//add by Mohammad
        if (dbType == DatabaseType.File) {
            return otherSelect("SELECT DISTINCT " + columns + " FROM " + tableName + " WHERE " + where + ";");
        } else {
            String SQL = jjDatabaseQuery.selectDistinct(tableName, columns);
            return otherSelect(SQL);
        }
    }

    public DefaultTableModel SelectDesc(String tableName, String column_for_DESC_order) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectDESC(tableName, "", column_for_DESC_order);
        return otherSelect(SQL);
    }

    public DefaultTableModel SelectDescLimit(String tableName, String column_for_DESC_order, String Limit) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectDESCLimit(tableName, "*", "", column_for_DESC_order, Limit);
        return otherSelect(SQL);
    }

    public DefaultTableModel SelectDescLimit(String tableName, String where, String column_for_DESC_order, String Limit) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectDESCLimit(tableName, "*", where, column_for_DESC_order, Limit);
        return otherSelect(SQL);
    }

    public DefaultTableModel SelectDesc(String tableName, String WHERE, String column_for_DESC_order) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectDESC(tableName, WHERE, column_for_DESC_order);
        return otherSelect(SQL);
    }

    public DefaultTableModel SelectAllDESC(String tableName, String column_for_DESC_order) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectAll(tableName + " ORDER BY " + column_for_DESC_order + " DESC");//The character ';' will add in 'selectAll();' 
        return otherSelect(SQL);
    }

    public DefaultTableModel SelectAllDESC(String tableName, String whichField, String column_for_DESC_order) {//add by Mohammad
        String SQL = jjDatabaseQuery.selectDESC(tableName, whichField, "1", column_for_DESC_order);//The character ';' will add in 'selectAll();' 
        return otherSelect(SQL);
    }
//==============================================================================

    public static DefaultTableModel SelectFromFile(String tableName) {
        Vector<String> vColumns = new Vector<String>();
        Vector rows, row;
        rows = new Vector();
        try {
            List<File> fileList = jjFile.getAllFileList(databasePath + "\\" + tableName, ".txt", "row-");
            List<List<String[]>> l = new ArrayList<List<String[]>>();
            for (int i = 0; i < fileList.size(); i++) {
                List<String> readLine = jjFileTxt.readLine(fileList.get(i));
                List<String[]> keyValue = jjFileTxt.getKeyValue(readLine);
                l.add(keyValue);
                row = new Vector();
                for (int j = 0; j < keyValue.size(); j++) {
                    if (!vColumns.contains(keyValue.get(j)[0])) {
                        vColumns.addElement(keyValue.get(j)[0]);
                    }
                }
            }
            for (int i = 0; i < l.size(); i++) {
                row = new Vector();
                for (int j = 0; j < vColumns.size(); j++) {
                    row.add("");
                }
                rows.addElement(row);
            }
            DefaultTableModel dtm = new DefaultTableModel(rows, vColumns);
            for (int i = 0; i < l.size(); i++) {
                for (int j = 0; j < l.get(i).size(); j++) {
                    dtm.setValueAt(l.get(i).get(j)[1], i, getColumnNumber(dtm, l.get(i).get(j)[0]));
                }
            }
            return dtm;
        } catch (Exception ex) {
            ex.printStackTrace();
            jjError.Handler(ex);
            return new DefaultTableModel();
        }
    }

    public static int getColumnNumber(JTable tbl, String ColumnName) {
        for (int i = 0; i < tbl.getColumnCount(); i++) {
            if (tbl.getColumnName(i).equals(ColumnName)) {
                return i;
            }
        }
        return -1;
    }

    public static int getColumnNumber(DefaultTableModel dtm, String ColumnName) {
        for (int i = 0; i < dtm.getColumnCount(); i++) {
            if (dtm.getColumnName(i).equals(ColumnName)) {
                return i;
            }
        }
        return -1;
    }

    public static List<Map<String, Object>> separateRow(DefaultTableModel dtm) {
        java.util.List<Map<String, Object>> parentMap = new java.util.ArrayList<Map<String, Object>>();
        if (dtm == null) {
            return parentMap;
        }
        for (int i = 0; i < dtm.getRowCount(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int j = 0; j < dtm.getColumnCount(); j++) {
                map.put(dtm.getColumnName(j), dtm.getValueAt(i, j));
            }
            parentMap.add(map);
        }
        return parentMap;
    }

    public boolean otherStatement(String query_insert_update_delete) {
        boolean b = true;
        try {
            ServerLog.Print(query_insert_update_delete);
            if (dbType == DatabaseType.Excel) {// if excel is not open
                PreparedStatement prepareStatement = connection.prepareStatement(query_insert_update_delete);
                prepareStatement.executeUpdate();
            } else {
                b = connection.createStatement().executeUpdate(query_insert_update_delete) > 0;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return false;
        }
        return b;
    }
//==============================================================================

    public synchronized DefaultTableModel otherSelect(String query_select_join) {
        try {
            ServerLog.Print(query_select_join);
            Vector rows, row;
            resultSet = connection.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < vColumns.size(); i++) {
                    // byte[] bytes = resultSet.getBytes(i + 1);
                    // row.addElement(new String(bytes, "windows-1256"));
                    // row.addElement(resultSet.getString(i + 1).replace("?", "ÛŒ"));
//                    String string = ;
//                    string = resultSet.getString(i + 1) == null ? "" : jjDatabaseQuery.statementToHtml(resultSet.getString(i + 1));
                    Object a = resultSet.getObject(i + 1);
                    row.addElement(a == null ? "" : jjDatabaseQuery.statementToHtml(a.toString()));
                }
                rows.addElement(row);
            }
            resultSet = null;
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return new DefaultTableModel();
        }
    }

    public String getBackup(String tableName) {
        try {
            StringBuffer s = new StringBuffer();
            String query_select_join = jjDatabaseQuery.selectAll(tableName);
            ServerLog.Print(query_select_join);
            Vector rows, row;
            resultSet = connection.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            Vector<Integer> vColumnsType = new Vector<Integer>();
            rows = new Vector();
            String col = "";
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
                col += (col.equals("") ? "" : ",") + "`" + metaData.getColumnName(i + 1) + "`";
                vColumnsType.add(metaData.getColumnType(i + 1));
            }
            while (resultSet.next()) {
                row = new Vector();
                s.append("INSERT INTO `" + databaseName + "`.`" + tableName + "` (" + col + ") VALUES(");
                String rowS = "";
                for (int i = 0; i < vColumns.size(); i++) {
                    rowS = i == 0 ? "" : rowS;
                    // byte[] bytes = resultSet.getBytes(i + 1);
                    // row.addElement(new String(bytes, "windows-1256"));
                    // row.addElement(resultSet.getString(i + 1).replace("?", "ÛŒ"));
//                    String string = ;
//                    string = resultSet.getString(i + 1) == null ? "" : jjDatabaseQuery.statementToHtml(resultSet.getString(i + 1));
                    String r = resultSet.getString(i + 1);
                    row.addElement(r == null ? "" : jjDatabaseQuery.statementToHtml(r));
                    rowS = (rowS.equals("") ? "" : ",")
                            + (vColumnsType.get(i) == 12 ? "'" : "")
                            + (vColumnsType.get(i) == -1 ? "'" : "")
                            + (r == null ? "" : jjDatabaseQuery.statementToHtml(r)
                                    + (vColumnsType.get(i) == 12 ? "'" : "")
                                    + (vColumnsType.get(i) == -1 ? "'" : ""));
                    s.append(rowS);
                }
                s.append(");\r\n");
                rows.addElement(row);
            }
            return s.toString();
        } catch (Exception ex) {
            jjError.Handler(ex);
            return "";
        }
    }

    public String emptyTable(String tableName) {
        try {
            StringBuffer s = new StringBuffer();
            delete(tableName, "");
            return s.toString();
        } catch (Exception ex) {
            jjError.Handler(ex);
            return "";
        }
    }

    public DefaultTableModel otherSelect(String query_select_join, int everyPageRowNo, int whitchPage) {
        try {
            Vector rows, row;
            ServerLog.Print(query_select_join);
            resultSet = connection.createStatement().executeQuery(query_select_join);
            ResultSetMetaData metaData = resultSet.getMetaData();
            Vector<String> vColumns = new Vector<String>();
            rows = new Vector();
            for (int i = 0; i < metaData.getColumnCount(); i++) {
                vColumns.add(metaData.getColumnName(i + 1));
            }
            int j = 0;
            while (resultSet.next()) {
                row = new Vector();
                for (int i = 0; i < vColumns.size(); i++) {
                    String string = resultSet.getString(i + 1);
                    row.addElement(string == null ? null : string);
                }
                if (whitchPage * everyPageRowNo <= j && (whitchPage + 1) * everyPageRowNo > j) {
                    rows.addElement(row);
                }
                j += 1;
            }
            return new DefaultTableModel(rows, vColumns);
        } catch (Exception ex) {
            jjError.Handler(ex);
            return new DefaultTableModel();
        }
    }
//==============================================================================

    /**
     *
     * get counter for Id of Tables
     */
    public static int getCounterFromFile(String counterPath) {
        try {
            File f = new File(counterPath);
            int ID = 1001;
            if (f.exists()) {
                String num = jjFileTxt.read(f);
                if (jjNumber.isDigit(num.trim())) {
                    ID = Integer.parseInt(num.trim());
                }
                jjFileTxt.write(f, "" + (ID + 1), false, false);
                return ID;
            } else {
                jjFileTxt.write(f, "" + (ID + 1), false, false);
                return ID;
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return 0;
        }
    }

    public void executeThisQuery(String query) throws SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query);
        System.out.println("DBAccessor.executeThisQuery() runs a query");

    }

    public void close() throws SQLException {
        connection.close();
        System.out.println("Database connection is closed ...");
    }
    public static StringBuilder testDb(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException, ClassNotFoundException {
        StringBuilder str = new StringBuilder("<div>hi</div>");
        PreparedStatement ps = db.connection.prepareStatement("insert INTO user_info (user_info_userName) values('محمد ثالثي');");
        ps.executeUpdate();
        ps = db.connection.prepareStatement("insert INTO user_info (user_info_userName) values('علی بینش پژوه کوهی');");
        ps.executeUpdate();
        ps = db.connection.prepareStatement("SELECT * FROM user_info");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2));
            str.append(result.getString(1)).append(" ").append(result.getString(2));
            str.append("<br>");
        }
        return str;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        jjDatabaseWeb db = jjDatabaseWeb.getInstance();
        PreparedStatement ps = db.connection.prepareStatement("SELECT * FROM user_info");
        ResultSet result = ps.executeQuery();
        while (result.next()) {
            System.out.println(result.getString(1) + " " + result.getString(2));
        }
    }
}
