/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kavenegar;

import com.kavenegar.api.ArrayOfLong;
import com.kavenegar.api.ArrayOfString;
import com.kavenegar.api.V1;
import com.kavenegar.api.V1Soap;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Holder;
import jj.jjDatabaseWeb;

/**
 *
 * @author Rashidi
 */
public class App {

    public static void SendSMS(String text, ArrayOfString receptor) {
//    public static void SendSMS(HttpServletRequest request, jjDatabaseWeb db, boolean isPost) {
        try {
//           String text=request.getParameter("smsText");
            String apikey = "7676666E4B744A303454624A376C4F4550687A2B6D513D3D"; // 9132015...
//            String apikey = "77326C565155764552664D796D544773554F6D4253773D3D"; // 936...
            String sender = "300002525";
            String message = text;
//            ArrayOfString receptor = new ArrayOfString();
            receptor.getString().add("09132015239");
            long unixdate = 0;
            int msgmode = 1;

            V1Soap ws;
            V1 wsService = new V1();
            ws = wsService.getV1Soap();
//            com.kavenegar.V1 service = new com.kavenegar.V1();
//            com.kavenegar.V1Soap port = service.getV1Soap();
            // TODO initialize WS operation arguments here
            Holder<Integer> status = new Holder<>();
            Holder<String> statusmessage = new Holder<>();
            Holder<ArrayOfLong> sendSimpleByApikeyResult = new Holder<>();
            ws.sendSimpleByApikey(apikey, sender, message, receptor, unixdate, msgmode, status, statusmessage, sendSimpleByApikeyResult);
            System.err.println(statusmessage.value);
            System.err.println(status.value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayOfString receptor = new ArrayOfString();
        receptor.getString().add("09132015239");
        SendSMS("سلام",receptor);
    }

}
