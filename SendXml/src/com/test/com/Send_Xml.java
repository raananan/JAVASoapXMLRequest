package com.test.com;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Send_Xml {
	/////////////////////////////////////////////////////
	//Function which get url and file for API call
	/////////////////////////////////////////////////////
	static void GetApiResult(String url, String GetFile) {
		 try {
		 URL obj = new URL(url);
		 HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		 con.setRequestMethod("POST");
		 con.setRequestProperty("Content-Type","text/xml; charset=utf-8");
		 
		 con.setDoOutput(true);
		 DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		 wr.writeBytes(GetFile);
		 wr.flush();
		 wr.close();
		 String responseStatus = con.getResponseMessage();
		 System.out.println(responseStatus);
		 BufferedReader in = new BufferedReader(new InputStreamReader(
		 con.getInputStream()));
		 String inputLine;
		 StringBuffer response = new StringBuffer();
		 while ((inputLine = in.readLine()) != null) {
		 response.append(inputLine);
		 }
		 in.close();
		 System.out.println("response:" + response.toString());
		 }
	     catch (Exception e) {
		 System.out.println(e);
	     }
	}
	////////////////////////////////
	//Main Class
	////////////////////////////////
 public static void main(String[] args) {
	 try {
	 String url_UserLogin = "https://test.api.system.simplyclub.co.il/TerminalService.asmx?op=UserAccountLogin";
	 String url_GetMemberDetails = "https://test.api.system.simplyclub.co.il/TerminalService.asmx?op=MemberGetDetails";
	 
	 String GetUserLogin_xml = "";
	 String MemberDetails_xml = "";
	 
	 GetUserLogin_xml = new String(Files.readAllBytes(Paths.get("UserLogin.xml")));//Reading the whole xml file of User accountLogin into the string
	 MemberDetails_xml = new String(Files.readAllBytes(Paths.get("MemberGetDetails.xml")));//Reading the whole xml file of MemberGetDetails into the string
	 
	 System.out.println("API result from UserLogin");
	 GetApiResult(url_UserLogin,GetUserLogin_xml);
	 
	 System.out.println("API result from GetMemberDetails");
	 GetApiResult(url_GetMemberDetails,MemberDetails_xml);
	 
	 } catch (Exception e) {
	 System.out.println(e);
	 }
   } 
}
 

 
		