package xzy.utils;

//从含有html标签的文本中获取纯文本
public class SimplifyText {
	 public static String StripHTML(String strHtml) {  
	     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //剔出<html>的标签    
	        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符    
	        return txtcontent;  
	   }  
}
