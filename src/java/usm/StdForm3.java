/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usm;
import java.util.HashMap;
import java.util.Map;
//این دو کلاس را اضافه کردیم زمانی که دستور ئپ را نیو کردیم
import javax.servlet.http.HttpServletRequest;
import javax.swing.table.DefaultTableModel;
import jj.jjDatabaseWeb;
/**
 *
 * @author shima
 */
public class StdForm3 {
     public static final String tableName = "std_form3";
    public static final String _id = "id";
     public static final String _q1 = "std_form3_q1";
    
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
}
