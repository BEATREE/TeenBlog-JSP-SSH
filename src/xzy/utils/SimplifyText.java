package xzy.utils;

//�Ӻ���html��ǩ���ı��л�ȡ���ı�
public class SimplifyText {
	 public static String StripHTML(String strHtml) {  
	     String txtcontent = strHtml.replaceAll("</?[^>]+>", ""); //�޳�<html>�ı�ǩ    
	        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//ȥ���ַ����еĿո�,�س�,���з�,�Ʊ��    
	        return txtcontent;  
	   }  
}
