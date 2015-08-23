/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usm;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
//این دو کلاس را اضافه کردیم زمانی که دستور ئپ را نیو کردیم
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import jj.Enum.DatabaseType;
import jj.jjDatabaseQuery;
import jj.jjDatabaseWeb;
import jj.jjError;
/**
 *
 * @author shima
 */
public class StdForm2 {
      private static DatabaseType dbType = DatabaseType.Access;
     public static final String tableName = "std_form2";
    public static final String _id = "id";
     public static final String _q1 = "std_form2_q1";
    
        public static String insert(HttpServletRequest request, jjDatabaseWeb db, boolean isPost)  {
            
     String q1= request.getParameter(_q1);
    Map<String,Object> map = new HashMap<String,Object>();
       map.put(_q1, q1);
       DefaultTableModel result=db.insert(tableName,map);
       if(result.getRowCount() !=0){
       return "";
       }
       else{
           return "خطا";
       }
       
    }
      
        
        
         public static String delete(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        String id = request.getSession().getAttribute("#"+_id).toString();
        try {
//            String result = db.delete(tableName, WHERE);
            if(db.delete(tableName, _id + " = '" + id + " ';")){
                 return "اطلاعات با موفقیت حذف شد";
            
            } else {
                return "خطا";
            }
        } catch (Exception ex) {
            jjError.Handler(ex);
            return "خطا";
        }
    }
}





