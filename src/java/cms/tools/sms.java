/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import cms.access.Access_User;
import com.kavenegar.api.ArrayOfInt;
import com.kavenegar.api.ArrayOfLong;
import com.kavenegar.api.ArrayOfString;
import com.kavenegar.api.V1;
import com.kavenegar.api.V1Soap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;
import jj.jjDatabaseWeb;

/**
 *
 * @author Rashidi
 */
public class sms {

    public static String apikey = "77326C565155764552664D796D544773554F6D4253773D3D"; // 936...
//            public static String apikey = "7676666E4B744A303454624A376C4F4550687A2B6D513D3D"; // 9132015...
    public static String tableName = "sms";
    public static String _id = "id";
    public static String _text = "sms_tsxt";
    public static String _sender = "sms_sender";
    public static String _receiver = "sms_receiver";
    public static String _characters = "sms_characters";
    public static String _status = "sms_status";
    public static int rul_ins = 51;

////// ------------- sendSMS() ------------->
    public static String sendSMS(ArrayOfString text, String receptorStr) throws SQLException {
        jjDatabaseWeb db;
        try {
            db = jjDatabaseWeb.getInstance();
//           String text=request.getParameter("smsText");
            ArrayOfString sender = new ArrayOfString();
//            System.out.println("receptor size : " + receptor.getString().size());
            System.out.println("receptor : " + receptorStr);
            System.out.println("text size : " + text.getString().size());

            System.out.println("sender size : " + sender.getString().size());
            ArrayOfInt msgmode = new ArrayOfInt();
//            String sender = "300002525";
//            String message = text;
            ArrayOfString receptor = new ArrayOfString();
            receptor = checkMobilenum(receptorStr);
            System.out.println("receptor size : " + receptor.getString().size());
            for (int i = 0; i < receptor.getString().size(); i++) {
                sender.getString().add("300002525");
            }
//            receptor.getString().add("09132015239");
            long unixdate = 0;
//            int msgmode = 1;
//            for(int i=0;i<receptor.getString().size();i++){
            msgmode.getInt().add(1);
//            }
            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleByApikeyResult = new Holder<>();

//            ws.sendAdvance(apikey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
            ws.sendArrayByApikey(apikey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
//            ws.sendSimpleByApikey(apikey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
//            ServerLog.Print(sendSimpleByApikeyResult.value);
            ServerLog.Print(statusmessage.value);
//            System.err.println(statusmessage.value);
            System.err.println(status.value);
            ///--------------- INSERT----- 
            System.out.println(">>  INSERT SMS INFO : ");
            for (int i = 0; i < receptor.getString().size(); i++) {
//                Map<String, Object> smsMap = new HashMap<String, Object>();
//                smsMap.put(_text, text);
//                smsMap.put(_sender, sender);
//                System.out.println("RECEPTOR : " + receptor.getString().get(i));
//                smsMap.put(_receiver, receptor.getString().get(i));
//                smsMap.put(_characters, text.length());
//                System.out.println("STATUS : " + statusmessage.value);
//                smsMap.put(_status, statusmessage.value);
                Map<String, Object> smsMap = new HashMap<String, Object>();
                smsMap.put(_text, text.getString().get(i));
                smsMap.put(_sender, sender.getString().get(0));
                System.out.println("RECEPTOR : " + receptor.getString().get(i));
                smsMap.put(_receiver, receptor.getString().get(i));
                smsMap.put(_characters, text.getString().get(i).length());
                System.out.println("STATUS : " + statusmessage.value);
                smsMap.put(_status, statusmessage.value);
//            smsMap.put(_status, status.value.indexOf(i));
                System.out.println("SMS INFO : " + smsMap);
                if (db.insert(tableName, smsMap).getRowCount() != 0) {
                    System.out.println("اطلاعات اس ام اس درج شد.");
                } else {
                    System.out.println("اطلاعات اس ام اس درج نشد.");

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
////// <------------- sendSMS() -------------
////// ------------- sendSMS() with request ------------->

    public static String sendSMS(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) throws SQLException {
        try {
            String hasAccess = Access_User.getAccessDialog(request, db, rul_ins);
            if (!hasAccess.equals("")) {
                return hasAccess;
            }
            String text = request.getParameter(_text);
//            String apikey = "7676666E4B744A303454624A376C4F4550687A2B6D513D3D"; // 9132015...
            String apikey = "77326C565155764552664D796D544773554F6D4253773D3D"; // 936...
            String sender = "300002525";
            ArrayOfString receptor = new ArrayOfString();
            receptor.getString().add(request.getParameter(_receiver));
            long unixdate = 0;
            int msgmode = 1;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
            // TODO initialize WS operation arguments here
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleByApikeyResult = new Holder<>();
            ws.sendSimpleByApikey(apikey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
//            ws.sendSimpleByApikey(apikey, sender, text, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
            ServerLog.Print(statusmessage);
            System.err.println(status.value);
            ///--------------- INSERT
            System.out.println(">>  INSERT SMS INFO : ");
            for (int i = 0; i < receptor.getString().size(); i++) {
                Map<String, Object> smsMap = new HashMap<String, Object>();
                smsMap.put(_text, text);
                smsMap.put(_sender, sender);
                System.out.println("RECEPTOR : " + receptor.getString().get(i));
                smsMap.put(_receiver, receptor.getString().get(i));
                smsMap.put(_characters, text.length());
                System.out.println("STATUS : " + statusmessage.value);
                smsMap.put(_status, statusmessage.value);
//            smsMap.put(_status, status.value.indexOf(i));
                System.out.println("SMS INFO : " + smsMap);
                if (db.insert(tableName, smsMap).getRowCount() != 0) {
                    System.out.println("اطلاعات اس ام اس درج شد.");
                } else {
                    System.out.println("اطلاعات اس ام اس درج نشد.");

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
////// <------------- sendSMS() with request -------------
////// ----------------- check mobileNum is true ------->

    public static ArrayOfString checkMobilenum(String MobileNumber) {
        try {
            if (MobileNumber == null) {
                MobileNumber = "0";
            }
            ServerLog.Print("...sending...\n  sms.checkMobilenum(" + MobileNumber + ");");
            String str = MobileNumber;
            str = str.replaceAll("\\s+", ",");
            str = str.replaceAll(",+", ",");
            str = str.replaceAll("[^0-9+]+", ",");
            String numbers[] = str.split(",");
//            List<String> numbersList = new ArrayList<String>();
            ArrayOfString numbersList = new ArrayOfString();
            for (int i = 0; i < numbers.length; i++) {
                ServerLog.Print(numbers[i] + " maches:" + numbers[i].matches("[(+989)(09)(9)]+[0-9]{9},{0,1}"));
                if (numbers[i].matches("[(+989)(09)(9)]+[0-9]{9},{0,1}")) {
                    numbersList.getString().add(numbers[i]);
                }
            }
            if (numbersList.getString().isEmpty()) {
                return null;
            } else {
                return numbersList;
            }

//            List<Long> result = new ArrayList<Long>();
//            if (numbersList.size() > 50) {// اگر لیست بزرگتر از مقدار مشخصی بود در جند نوبت اس ام اس ارسال شود
//                ServerLog.Print("send to :" + numbersList.size() + " Numbers ...");
//                for (int i = 0; i < numbersList.size() / 50; i++) {
//                    ServerLog.Print("send to :" + (i * 50) + "to" + ((i + 1) * 50 - 1));
//                    result.addAll(KvNgrSMS.send(content, numbersList.subList(i * 50, (i + 1) * 50 - 1)));
////                    result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//                }
//                if (numbersList.size() % 50 > 0) {// برای قسمت آخر اگر تقسیم لیست به بخش های پنجاه تایی کاملا مساوی ممکن نبود
//                    ServerLog.Print("send to :" + ((numbersList.size() / 50) * 50) + "to" + (numbersList.size()));
//                    result.addAll(KvNgrSMS.send(content, numbersList.subList((numbersList.size() / 50) * 50, numbersList.size())));
////                    result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//                }
//            } else {// اگر اندازه لیست کمتر از مقدار مشخص بود
//                result.addAll(KvNgrSMS.send(content, numbersList));
////                result.add(Long.MAX_VALUE);// برای تست رمانی که می خواهیم پیامک ارسال نشود
//            }
//            ServerLog.Print(result.toString());
//            String resultstr = "";
//            resultstr += result.size() > 2 ? result.size() * 50 + "تعداد" : result.toString();
//            return resultstr;
//        } catch (Exception ex) {
//            return "...  sms.sendOneSms(" + content + "," + MobileNumber + ");" + ex;
//        }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

////// <----------------- check mobileNum is true -------
////// ---------------- main
    public static void main(String[] args) throws SQLException {
//        ArrayOfString receptor = new ArrayOfString();
        ArrayOfString text = new ArrayOfString();
//        receptor.getString().add("09132015239");
////        receptor.getString().add("09198924470");
//        receptor.getString().add("09138139196");
        text.getString().add("سلام");
        text.getString().add("سلام");
//        sendSMS("سلام", receptor);
        String receptor = "09132015239,09198924470";
        sendSMS(text, receptor);
    }

}
