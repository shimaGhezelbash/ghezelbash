package jj;

import cms.tools.ServerLog;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author Milad Jamalzadeh 09132686053
 */
public class jjDatabaseQuery {

    //------------------------------------------------------------------------------
    /**
     * insert to tbl Map<String, Object> params = new HashMap();
     * params.put("columnName", "Value");
     *
     */
    public static String insert(String tableName, Map<String, Object> columnNameAndValues) {
        Object obj[] = columnNameAndValues.keySet().toArray();
        String fieldName = "";
        String value = "";
        for (int i = 0; i < obj.length; i++) {
            fieldName += (fieldName.equals("") ? "" : ",") + obj[i].toString();
            boolean isText = columnNameAndValues.get(obj[i]).getClass().isInstance("String");
//            boolean isText = columnNameAndValues.get(obj[i]).getClass().toString().endsWith("String");
            String val = columnNameAndValues.get(obj[i]) == null ? "" : columnNameAndValues.get(obj[i]).toString();
            val = htmlToStatement(val);
            value += (value.equals("") ? "" : ",") + (isText ? ("'" + val + "'") : val);
        }
        String SQL = "INSERT INTO " + tableName + " (" + fieldName + ") VALUES (" + value + ");";
        return SQL;
    }

    public static String htmlToStatement(String str) {

//        StringBuffer sb = new StringBuffer();
//        int len = str.length();
//        char c;
//        for (int i = 0; i < len; i++) {
//            c = str.charAt(i);
//            switch (c) {
//                case '&':
//                    sb.append("!and!");
//                    break;
//                case '<':
//                    sb.append("!small!");
//                    break;
//                case '>':
//                    sb.append("!big!");
//                    break;
//                case '"':
//                    sb.append("!2quot!");
//                    break;
//                case '\'':
//                    sb.append("!1quot!");
//                    break;
//                default:
//                    sb.append(c);
//                    break;
//            }
//        }
//
//        return sb.toString();
//        return str.replace("'", "''");
//        return str.replace("\\", "/");
//        return str.replace("\\", "/").replace("'", "\\'").replace("\n", "").replace("!and!", "&").replace("!dr!", "%").replace("!small!", "<").replace("!big!", ">");
        return str.replace("\\", "/").replace("'", "\\'").replace("\n", "").replace("\r\n", "");
    }

    public static String statementToHtml(String src) {
//        src = src.replace("!1quot!", "'");
//        src = src.replace("!2quot!", "\\\"");
//        src = src.replace("!and!", "&");
//        src = src.replace("!dr!", "%");
//        src = src.replace("\"", "");
//        src = src.replace("'", "\'");
//        src = src.replace("\'", "\\'");
//        src = src.replace("\"", "\\\"");
        src = src.replace("ي", "ی");
        return src.replace("\n", "");
    }

    public static String MapToString(Map<String, Object> map) {
        String keyValue = "";
        Object obj[] = map.keySet().toArray();
        for (int i = 0; i < obj.length; i++) {
            boolean isText = map.get(obj[i]).getClass().toString().endsWith("String");
            keyValue += (keyValue.equals("") ? "" : ",") + obj[i].toString() + "="
                    + (isText ? ("'" + htmlToStatement(map.get(obj[i]).toString()) + "'") : htmlToStatement(map.get(obj[i]).toString()));
        }
        return keyValue;
    }

    public static String update(String tableName, Map<String, Object> columnNameAndValues, String WHERE) {
        String set = MapToString(columnNameAndValues);
        String SQL = "UPDATE " + tableName + " SET " + set + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String delete(String tableName, String WHERE) {
        String SQL = "DELETE FROM " + tableName + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String selectAll(String tableName) {
        String SQL = "SELECT * FROM " + tableName + "  ;";
        return SQL;
    }

    public static String selectAll(String tableName, String whichField) {
        String SQL = "SELECT " + (whichField.equals("") ? "*" : whichField) + " FROM " + tableName + " ;";
        return SQL;
    }

    public static String select(String tableName, String WHERE) {
        String SQL = "SELECT * FROM " + tableName + " " + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String select(String tableName, String whichField, String WHERE) {
        whichField = whichField == null ? "" : whichField;
        WHERE = WHERE == null ? "" : WHERE;
        String SQL = "SELECT " + (whichField.equals("") ? "*" : whichField)
                + " FROM " + tableName + " "
                + (WHERE.equals("") ? ";" : " WHERE " + WHERE + " ;");
        return SQL;
    }

    public static String selectDistinct(String tableName, String columns) {//add by Mohammad
        String SQL = "SELECT DISTINCT "+columns +" FROM " + tableName +";" ;
        return SQL;
    }

    public static String selectDESC(String tableName, String WHERE, String DESC_sort_column) {//add by Mohammad
        String SQL = "SELECT * FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC;");
        return SQL;
    }
    public static String selectDESC(String tableName,String whichField ,String WHERE, String DESC_sort_column) {//add by Mohammad
        String SQL = "SELECT "+whichField+" FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC;");
        return SQL;
    }
    public static String selectDESCLimit(String tableName,String whichField ,String WHERE, String DESC_sort_column,String Limit) {//add by Mohammad
        String SQL = "SELECT "+whichField+" FROM " + tableName + ((WHERE.equals("") ? "" : (" WHERE " + WHERE)) + " ORDER BY " + DESC_sort_column + " DESC LIMIT " + Limit + " ;" );
        return SQL;
    }

    public static String count(String tblName) {
        String SQL = "SELECT count(*) FROM " + tblName;
        return SQL;
    }

    public static String max(String tblName, String fieldName) {
        String SQL = "SELECT MAX(" + fieldName + ") FROM " + tblName + " ; ";
        return SQL;
    }
//    public static enum java_Type {
//        System, Windows, Windows_Classic, Metal, Nimbus, CDE_Motif, Napkin;
//    }
    public static enum db_Row_Properties {

        PrimaryKey, OutoIncreament, NotNull;
    }
//

    public static class db_Row {

        String RowName;
        Object java_Type;
        Object SQL_Type;
        Object defaultValue = "";
        String SQL_Properties = "";
        public boolean isPRIMARY = false;

        /**
         *
         * in boolean type 0 default value is false and 1 is true <br/>
         */
        public db_Row(String RowName, Object TypeInstance, Object defaultValue, db_Row_Properties... properties) {
            this.RowName = RowName;
            if (TypeInstance.getClass().toString().toUpperCase().contains("Boolean")) {
                this.SQL_Type = "tinyint(1)";
                this.java_Type = "boolean";
            } else if (TypeInstance.getClass().toString().endsWith("String")) {
                this.java_Type = "String";
                int typ = 255;
                if (TypeInstance == null) {
                    this.SQL_Type = "varchar(" + typ + ")";
                } else if (TypeInstance.toString().equals("")) {
                    this.SQL_Type = "varchar(" + typ + ")";
                } else if (jjNumber.isDigit(TypeInstance.toString())) {
                    typ = Integer.parseInt(TypeInstance.toString());
                    if (typ > 255) {
                        this.SQL_Type = "blob";
                    } else {
                        this.SQL_Type = "varchar(" + typ + ")";
                    }
                } else {
                    this.SQL_Type = "varchar(" + typ + ")";
                }
            } else if (TypeInstance.getClass().toString().endsWith("Integer")) {
                this.SQL_Type = "int(11)";
                this.java_Type = "int";
            } else if (TypeInstance.getClass().toString().endsWith("Date")) {
                this.java_Type = "Help.MyDate";
                this.SQL_Type = "int(11)";
            }
            for (int i = 0; i < properties.length; i++) {
                if (properties[i] == db_Row_Properties.PrimaryKey) {
                    this.SQL_Properties += " PRIMARY KEY";
                    isPRIMARY = true;
                } else if (properties[i] == db_Row_Properties.NotNull) {
                    this.SQL_Properties += " NOT NULL";
                } else if (properties[i] == db_Row_Properties.OutoIncreament) {
                    this.SQL_Properties += " auto_increment";
                }
            }
            if (defaultValue == null || defaultValue.toString().equals("")) {
                this.defaultValue = "";
            } else if (defaultValue.getClass().toString().endsWith("Boolean")) {
                this.defaultValue = "";

            }
            this.defaultValue = " default '" + defaultValue.toString() + "'";
        }
    }
}
